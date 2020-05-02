package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.SmsHomeAdvertiseDao;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeAdvertise;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeAdvertiseExample;
import club.banyuan.zgMallMgt.dto.SmsHomeAdvertiseResp;
import club.banyuan.zgMallMgt.service.OssFileService;
import club.banyuan.zgMallMgt.service.SmsHomeAdvertiseService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.SMS_HOME_ADVERTISE_NOT_EXIST;

@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {

    @Autowired
    private SmsHomeAdvertiseDao smsHomeAdvertiseDao;
    @Autowired
    private OssFileService ossFileService;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String name, Integer type, Date endTime) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeAdvertiseExample smsHomeAdvertiseExample = new SmsHomeAdvertiseExample();
        SmsHomeAdvertiseExample.Criteria criteria = smsHomeAdvertiseExample.createCriteria();
        if (StrUtil.isNotBlank(name)){
            criteria.andNameLike(StrUtil.concat(true, "%",name,"%"));
        }
        if (type!=null){
            criteria.andTypeEqualTo(type);
        }
        if (endTime!=null){
            criteria.andEndTimeEqualTo(endTime);
        }
        List<SmsHomeAdvertise> smsHomeAdvertises = smsHomeAdvertiseDao.selectByExample(smsHomeAdvertiseExample);
        PageInfo<SmsHomeAdvertise> pageInfo = new PageInfo<>(smsHomeAdvertises);
        List<SmsHomeAdvertiseResp> collect = smsHomeAdvertises.stream().map(t -> {
            SmsHomeAdvertiseResp smsHomeAdvertiseResp = new SmsHomeAdvertiseResp();
            BeanUtil.copyProperties(t, smsHomeAdvertiseResp);
            return smsHomeAdvertiseResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    public Integer create(SmsHomeAdvertise smsHomeAdvertise) {
        if (BeanUtil.isEmpty(smsHomeAdvertise)){
            throw new ReqFailException(SMS_HOME_ADVERTISE_NOT_EXIST);
        }
        return smsHomeAdvertiseDao.insert(smsHomeAdvertise);
    }

    @Override
    public SmsHomeAdvertiseResp getInfo(Long id) {
        SmsHomeAdvertise smsHomeAdvertise = smsHomeAdvertiseDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsHomeAdvertise)){
            throw new ReqFailException(SMS_HOME_ADVERTISE_NOT_EXIST);
        }
        SmsHomeAdvertiseResp smsHomeAdvertiseResp = new SmsHomeAdvertiseResp();
        BeanUtil.copyProperties(smsHomeAdvertise, smsHomeAdvertiseResp);
        return smsHomeAdvertiseResp;
    }

    @Override
    public Integer update(Long id, SmsHomeAdvertise smsHomeAdvertise) {
        SmsHomeAdvertise smsHomeAdvertise1 = smsHomeAdvertiseDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsHomeAdvertise1)){
            throw new ReqFailException(SMS_HOME_ADVERTISE_NOT_EXIST);
        }
        smsHomeAdvertise.setId(id);
        String pic = smsHomeAdvertise1.getPic();
        if(!smsHomeAdvertise.getPic().equals(pic)){
            try {
                ossFileService.delete(pic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return smsHomeAdvertiseDao.updateByPrimaryKey(smsHomeAdvertise);
    }

    @Override
    public Integer delete(List<Long> ids) {
        List<String> pics=new ArrayList<String>();
        ids.forEach(t -> {
            SmsHomeAdvertise smsHomeAdvertise = smsHomeAdvertiseDao.selectByPrimaryKey(t);
            if (BeanUtil.isEmpty(smsHomeAdvertise)) {
                throw new ReqFailException(SMS_HOME_ADVERTISE_NOT_EXIST);
            }
            pics.add(smsHomeAdvertise.getPic());
        });
        SmsHomeAdvertiseExample smsHomeAdvertiseExample = new SmsHomeAdvertiseExample();
        SmsHomeAdvertiseExample.Criteria criteria = smsHomeAdvertiseExample.createCriteria();
        criteria.andIdIn(ids);
        int i = smsHomeAdvertiseDao.deleteByExample(smsHomeAdvertiseExample);
        if (CollUtil.isNotEmpty(pics)) {
            pics.forEach(t -> {
                try {
                    ossFileService.delete(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return i;
    }

    @Override
    public Integer updateStatus(Long id, Integer status) {
        SmsHomeAdvertise smsHomeAdvertise = smsHomeAdvertiseDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsHomeAdvertise)){
            throw new ReqFailException(SMS_HOME_ADVERTISE_NOT_EXIST);
        }
        smsHomeAdvertise.setStatus(status);
        return smsHomeAdvertiseDao.updateByPrimaryKey(smsHomeAdvertise);
    }
}
