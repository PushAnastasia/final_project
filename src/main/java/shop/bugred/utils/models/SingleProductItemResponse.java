package shop.bugred.utils.models;

import java.util.Objects;

public class SingleProductItemResponse {

    private String method;
    private String status;
    private ProductItem result;

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

    public ProductItem getResult() {
        return result;
    }

    public void setResult(ProductItem result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SingleProductItemResponse{" +
                "method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleProductItemResponse response = (SingleProductItemResponse) o;
        return Objects.equals(method, response.method) &&
                Objects.equals(status, response.status) &&
                Objects.equals(result, response.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, status, result);
    }
}
