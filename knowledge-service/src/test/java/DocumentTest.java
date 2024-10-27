import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.kb.knowledge.KnowledgeApplication;
import com.kb.knowledge.domain.dto.KnowledgeDoc;
import com.kb.knowledge.domain.po.Knowledge;
import com.kb.knowledge.domain.vo.KsAllVO;
import com.kb.knowledge.service.KnowledgeService;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;


@SpringBootTest(properties = "spring.profiles.active=local",classes = KnowledgeApplication.class)
public class DocumentTest {
    private RestHighLevelClient client;

    @Autowired
    private KnowledgeService knowledgeService;

    @BeforeEach
    void setUp() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "software-ip"));

        this.client = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.184.130:9200"))
                        .setHttpClientConfigCallback(httpClientBuilder ->
                                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
        );
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }


    @Test
    void testAddDocument() throws IOException {
        // 1.根据id查询知识数据
        System.out.println(111);

        Knowledge kById = knowledgeService.getKById(1);


        // 2.转换为文档类型
        KnowledgeDoc knowledgeDoc = BeanUtil.copyProperties(kById, KnowledgeDoc.class);


        // 3.将ItemDTO转json
        String doc = JSONUtil.toJsonStr(knowledgeDoc);

        // 1.准备Request对象
        IndexRequest request = new IndexRequest("ks").id(knowledgeDoc.getId().toString());
        // 2.准备Json文档
        request.source(doc, XContentType.JSON);
        // 3.发送请求
        client.index(request, RequestOptions.DEFAULT);
    }


    @Test
    void testGetDocumentById() throws IOException {
        // 1.准备Request对象
        GetRequest request = new GetRequest("ks").id("1");
        // 2.发送请求
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 3.获取响应结果中的source
        String json = response.getSourceAsString();

        KnowledgeDoc knowledgeDoc = JSONUtil.toBean(json, KnowledgeDoc.class);
        System.out.println("knowledgeDoc= " + knowledgeDoc);
    }


    @Test
    void testDeleteDocument() throws IOException {
        // 1.准备Request，两个参数，第一个是索引库名，第二个是文档id
        DeleteRequest request = new DeleteRequest("ks", "1");
        // 2.发送请求
        client.delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testUpdateDocument() throws IOException {
        // 1.准备Request
        UpdateRequest request = new UpdateRequest("ks", "1");
        // 2.准备请求参数
        request.doc(
                "kbaseId", 1
        );
        // 3.发送请求
        client.update(request, RequestOptions.DEFAULT);
    }


    @Test
    void testLoadItemDocs() throws IOException {
        //查询所有知识
        List<Knowledge> ks = knowledgeService.getKs();
        // 1.创建Request
        BulkRequest request = new BulkRequest("items");
        // 2.准备参数，添加多个新增的Request
        for (Knowledge knowledge : ks) {
            // 2.1.转换为文档类型ItemDTO
            KnowledgeDoc knowledgeDoc = BeanUtil.copyProperties(ks, KnowledgeDoc.class);
            // 2.2.创建新增文档的Request对象
            request.add(new IndexRequest()
                    .id(knowledge.getId().toString())
                    .source(JSONUtil.toJsonStr(knowledgeDoc), XContentType.JSON));
        }
        // 3.发送请求
        client.bulk(request, RequestOptions.DEFAULT);
    }

}
