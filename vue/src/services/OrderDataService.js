import http from "../http-commons";

class OrderDataService {
    createOrder(data) {
        return http.post("/api/orders", data);
    }
    getAllOrders() {
        return http.get("/api/v3_1/orders");
    }
}
export default new OrderDataService();