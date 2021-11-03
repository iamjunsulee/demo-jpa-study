import http from "../http-commons";

class MemberDataService {
    getAll() {
        return http.get("/api/v2/members");
    }

    join(data) {
        return http.post("/api/v2/members", data);
    }
}
export default new MemberDataService();