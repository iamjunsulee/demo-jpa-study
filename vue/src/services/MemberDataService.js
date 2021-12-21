import http from "../http-commons";

class MemberDataService {
    getAll() {
        return http.get("/api/v2/members");
    }
    join(data) {
        return http.post("/api/v2/members", data);
    }
    findByName(name) {
        return http.get(`/api/members/${name}`);
    }
    deleteMember(id) {
        return http.delete(`/api/members/${id}`);
    }
}
export default new MemberDataService();