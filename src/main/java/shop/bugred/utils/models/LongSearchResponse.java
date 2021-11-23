package shop.bugred.utils.models;

import java.util.List;

public class LongSearchResponse {

    private String method;
    private String status;
    private List<ItemShortVersion> result;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemShortVersion> getResult() {
        return result;
    }

    public void setResult(List<ItemShortVersion> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LongSearchResponse{" +
                "method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
