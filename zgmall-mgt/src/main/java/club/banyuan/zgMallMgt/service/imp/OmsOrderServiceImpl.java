package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.OmsOrderDao;
import club.banyuan.zgMallMgt.dao.entity.OmsOrderExample;
import club.banyuan.zgMallMgt.service.OmsOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    @Autowired
    private OmsOrderDao omsOrderDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = omsOrderExample.createCriteria();
        omsOrderDao.selectByExample(omsOrderExample);

        return ResponsePage.setPages(null,null);

    }
}
