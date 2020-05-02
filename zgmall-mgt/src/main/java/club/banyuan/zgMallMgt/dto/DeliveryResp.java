package club.banyuan.zgMallMgt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DeliveryResp implements Serializable {

    /**
     * orderId : 13
     * orderSn : 201809150102000002
     * receiverName : 大梨
     * receiverPhone : 18033441849
     * receiverPostCode : 518000
     * address : 广东省深圳市福田区东晓街道
     * deliveryCompany : 顺丰快递
     * deliverySn : 4382754784789236
     */

    @NotNull(message = "orderId不能为空")
    private Long orderId;
    @NotBlank(message = "orderSn不能为空")
    private String orderSn;
    @NotBlank(message = "receiverName不能为空")
    private String receiverName;
    @NotBlank(message = "receiverPhone不能为空")
    private String receiverPhone;
    @NotBlank(message = "receiverPostCode不能为空")
    private String receiverPostCode;
    @NotBlank(message = "address不能为空")
    private String address;
    @NotBlank(message = "deliveryCompany不能为空")
    private String deliveryCompany;
    @NotBlank(message = "deliverySn不能为空")
    private String deliverySn;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }
}
