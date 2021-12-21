import http from "../http-commons";

class MemberDataService {
    getAll() {
        return http.get("/api/v2/members");
    }
    join(data) {
        return http.post("/api/v2/members", data);
    }
    findByName(name) {
        return http.get(`/api/v2/members?name=${name}`);
    }
    findById(id) {
        return http.get(`/api/members/${id}`);
    }
    deleteMember(id) {
        return http.delete(`/api/members/${id}`);
    }
    updateMember(id, data) {
        return http.put(`/api/members/${id}`, data);
    }
}
export default new MemberDataService();