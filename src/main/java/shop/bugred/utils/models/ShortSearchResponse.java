package shop.bugred.utils.models;

import java.util.List;
import java.util.Objects;

public class ShortSearchResponse {
    private String method;
    private String status;
    private List<ProductItem> result;

    public List<ProductItem> getResult() {
        return result;
    }

    public void setResult(List<ProductItem> result) {
        this.result = result;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortSearchResponse that = (ShortSearchResponse) o;
        return Objects.equals(method, that.method) &&
                Objects.equals(status, that.status) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, status, result);
    }

    @Override
    public String toString() {
        return "ShortSearchResponse{" +
                "method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
