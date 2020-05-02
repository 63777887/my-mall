package club.banyuan.zgMallMgt.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OmsUpdateApplyResp implements Serializable {

    /**
     * companyAddressId : 1
     * handleMan : admin
     * handleNote : null
     * receiveMan : admin
     * receiveNote : null
     * returnAmount : 0
     * status : 3
     */

    private Long companyAddressId;
    private String handleMan;
    private String handleNote;
    private String receiveMan;
    private String receiveNote;
    private BigDecimal returnAmount;
    private int status;

    public Long getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(Long companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public String getHandleMan() {
        return handleMan;
    }

    public void setHandleMan(String handleMan) {
        this.handleMan = handleMan;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    public String getReceiveNote() {
        return receiveNote;
    }

    public void setReceiveNote(String receiveNote) {
        this.receiveNote = receiveNote;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
