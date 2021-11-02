import http from "../http-commons";

class MemberDataService {
    getAll() {
        return http.get("/api/v2/members")
    }
}
export default new MemberDataService();