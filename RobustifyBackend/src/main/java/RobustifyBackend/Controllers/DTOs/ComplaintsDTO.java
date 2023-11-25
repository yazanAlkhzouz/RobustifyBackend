package RobustifyBackend.Controllers.DTOs;

public class ComplaintsDTO {

        private Long orderId;
        private String defect;
        private String comment;


    public ComplaintsDTO() {

    }

    public ComplaintsDTO(Long orderId, String defect, String comment) {
        this.orderId = orderId;
        this.defect = defect;
        this.comment = comment;

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}
