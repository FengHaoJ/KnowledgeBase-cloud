package com.kb.knowledge.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.kb.knowledge.domain.dto.KnowledgeAddDTO;
import com.kb.knowledge.domain.dto.KnowledgePreviewUpdateDTO;
import com.kb.knowledge.domain.dto.KnowledgeUpdateDTO;
import com.kb.knowledge.domain.po.Knowledge;
import com.kb.knowledge.domain.po.KnowledgeBase;
import com.kb.knowledge.domain.po.KnowledgePreview;
import com.kb.knowledge.domain.vo.KContentVO;
import com.kb.knowledge.domain.vo.KIdAndNameVO;
import com.kb.knowledge.domain.vo.KsAllVO;
import com.kb.knowledge.mapper.KnowledgeMapper;
import com.kb.knowledge.service.KnowledgeService;
import com.kb.common.constant.MessageConstant;
import com.kb.common.context.BaseContext;
import com.kb.common.exception.AccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class KnowServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeMapper knowledgeMapper;


    /**
     * 查询所有知识
     * @return
     */
    @Override
    public List<KsAllVO> getAllKs() {
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);
        //分两步，先查询当前id创建/或者有查看权限的所有知识库

        //1.查询当前id创建的/或者有查看权限的所有知识库【更新为查看所有知识】
        List<KnowledgeBase> allKbs = knowledgeMapper.getAllKbs();

        //[KnowledgeBase(id=1, name=排序算法, user_id=null, createTime=2024-05-12T21:09:01, updateTime=2024-05-12T21:09:03, permission=0),
        // KnowledgeBase(id=2, name=查找算法, user_id=null, createTime=2024-05-15T22:21:15, updateTime=2024-05-15T22:21:18, permission=0)]
        System.out.println(allKbs);
        List<KsAllVO> ksAllVOList = new ArrayList<>();
        for(KnowledgeBase knowledgeBase : allKbs){
            //创建KsAllVO对象
            KsAllVO ksAllVO = new KsAllVO();
            ksAllVO.setId(knowledgeBase.getId());
            ksAllVO.setKbname(knowledgeBase.getName());

            //根据知识库ID查询对应的知识列表
            List<Knowledge> knowledgeList = knowledgeMapper.getKnowledgeByKbId(knowledgeBase.getId());

            // 创建存储知识的列表
            List<KIdAndNameVO> KIdAndNameVOList = new ArrayList<>();

            // 遍历知识列表，将每个知识信息转换为 KnowledgeVO 对象并添加到 knowledgeVOList 中
            for (Knowledge knowledge : knowledgeList) {
                KIdAndNameVO KIdAndNameVO = new KIdAndNameVO();
                KIdAndNameVO.setId(knowledge.getId());
                KIdAndNameVO.setName(knowledge.getName());
                // 设置其他属性
                KIdAndNameVOList.add(KIdAndNameVO);
            }
            // 将 knowledgeVOList 设置到 ksAllVO 对象中
            ksAllVO.setKnowledge(KIdAndNameVOList);

            // 将 ksAllVO 添加到 ksAllVOList 中
            ksAllVOList.add(ksAllVO);

        }
        return ksAllVOList;
    }

    /**
     * 根据知识库查询知识
     * @param kbId
     * @return
     */
    @Override
    public KsAllVO getKsById(Long kbId) {
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);
        List<Knowledge> knowledges = knowledgeMapper.getKnowledgeByKbId(kbId);
        KsAllVO ksAllVO = new KsAllVO();
        ksAllVO.setId(kbId);
        String name = knowledgeMapper.getNameByKbId(kbId);
        ksAllVO.setKbname(name);
        List<KIdAndNameVO> KIdAndNameVOList = new ArrayList<>();
        for(Knowledge knowledge:knowledges){
            KIdAndNameVO KIdAndNameVO = new KIdAndNameVO();
            KIdAndNameVO.setId(knowledge.getId());
            KIdAndNameVO.setName(knowledge.getName());
            KIdAndNameVOList.add(KIdAndNameVO);
        }
        ksAllVO.setKnowledge(KIdAndNameVOList);
        return ksAllVO;

    }

    /**
     * 获取知识详情
     * @param kbId
     * @param kId
     * @return
     */
    @Override
    public KContentVO getContent(Long kbId, Long kId) {
        //获取当前登录用户
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);

        //1.判断当前用户是否有访问（拥有）这个知识库【取消这个逻辑】
        //实现一个mapper，返回当前用户知识库列表

//        List<Long> allKbaseId= knowledgeMapper.getAllKbaseIdByUserId(currentId);
//        if(allKbaseId.contains(kbId)){
//            //2.判断当前用户是否有访问（拥有）这个知识
//            List<Long> allKnowledgeByKbaseId = knowledgeMapper.getAllKnowledgeByKbaseId(kbId);
//            if(allKnowledgeByKbaseId.contains(kId)){
//                //3.都成立则返回这个知识
//                Knowledge knowledge = knowledgeMapper.gerKnowledgeDetailById(kbId, kId);
//                //由于目前不知道返回什么，暂时先返回所有信息
//                KContentVO kContentVO = new KContentVO();
//                BeanUtil.copyProperties(knowledge,kContentVO);
//                return kContentVO;
//            }else {
//                throw new ResourceNotExistException(MessageConstant.RESOURCE_NOTEXIST);
//            }
//        }else{
//            throw new ResourceNotExistException(MessageConstant.RESOURCE_NOTEXIST);
//        }

        Knowledge knowledge = knowledgeMapper.gerKnowledgeDetailById(kbId, kId);
        //由于目前不知道返回什么，暂时先返回所有信息
        KContentVO kContentVO = new KContentVO();
        BeanUtil.copyProperties(knowledge,kContentVO);
        return kContentVO;

    }

    @Override
    public void saveKnowledge(KnowledgeAddDTO knowledgeAddDTO) {
        //获取当前登录用户
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);
        //防御性编程，判断当前的知识库id属不属于（）有没有权限访问【考虑到权限系统还没有加入暂留】

        Knowledge knowledge = new Knowledge();
        BeanUtil.copyProperties(knowledgeAddDTO,knowledge);

        knowledge.setUserId(currentId);
        knowledge.setCreateTime(LocalDateTime.now());
        knowledge.setUpdateTime(LocalDateTime.now());
        knowledgeMapper.insertKnowledge(knowledge);
        log.info("kid：{}",knowledge.getId());
        String usernameByUserId = knowledgeMapper.getUsernameByUserId(knowledge.getUserId());
        //同时创建知识预览
        knowledgeMapper.createKnowledgePreview(knowledge.getId(), knowledge.getName(), usernameByUserId,knowledge.getKbaseId());


    }

    @Override
    public void updateKnowledge(Long kbId, Long kId, KnowledgeUpdateDTO knowledgeUpdateDTO) {
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);

        Knowledge knowledge = new Knowledge();
        BeanUtil.copyProperties(knowledgeUpdateDTO,knowledge);

        knowledge.setUpdateTime(LocalDateTime.now());
        knowledge.setId(kId);
        knowledge.setKbaseId(kbId);
        log.info("修改的knowledge{}",knowledge);
        knowledgeMapper.updateKnowledge(knowledge);

        //修改知识库更新时间
        knowledgeMapper.updateKBUpdateTime(kbId,LocalDateTime.now());
    }

    @Override
    public void deleteKnowledge(Long kbId, Long kId) {
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);
        knowledgeMapper.deleteKnowledge(kbId, kId);
    }

    @Override
    public KnowledgePreview getKnowledgePreview(Long kbId, Long kId) {
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);
        KnowledgePreview knowledgePreview = knowledgeMapper.getKnowledgePreview(kId);
        return knowledgePreview;
    }

    @Override
    public void updateKnowledgePreview(KnowledgePreviewUpdateDTO knowledgePreviewUpdateDTO) {
        Long currentId = BaseContext.getCurrentId();
        if(currentId==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        log.info("currentId:{}",currentId);
        //构造实体
        //id，kid均已判断相同且不为空
        KnowledgePreview knowledgePreview = new KnowledgePreview();
//        knowledgePreview.setId(knowledgePreviewUpdateDTO.getId());
        //todo 创建知识时同步创建知识预览表
        BeanUtil.copyProperties(knowledgePreviewUpdateDTO,knowledgePreview);
        knowledgePreview.setKbaseId(null);
        knowledgePreview.setName(null);
        knowledgePreview.setAuthors(null);
        log.info("更新内容{}",knowledgePreview);
        knowledgeMapper.updateKnowledgePreview(knowledgePreview);

    }

    @Override
    public Knowledge getKById(long kid) {
        return knowledgeMapper.getKnowledgeById(kid);
    }

    @Override
    public List<Knowledge> getKs() {
        return knowledgeMapper.getAllKs();
    }


}
