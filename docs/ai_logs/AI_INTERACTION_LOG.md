# NHẬT KÝ TƯƠNG TÁC VÀ SỬ DỤNG TRỢ LÝ AI (AI INTERACTION LOG)

- **Tên dự án:** Dorm Utility Management System (Hệ thống Quản lý Ký túc xá & Tiền điện nước)
- **Môn học:** PRO192 | **Lớp:** SE21B | **Nhóm:** Group 1
- **Vị trí tài liệu:** `docs/ai_logs/AI_INTERACTION_LOG.md`

---

## 1. Quy ước ghi nhật ký sử dụng AI
Theo yêu cầu đề bài môn học PRO192, việc sử dụng các công cụ hỗ trợ bằng Trí tuệ Nhân tạo (GenAI) phải tuân thủ nghiêm ngặt các nguyên tắc sau:
1. **Trách nhiệm cá nhân:** Mỗi thành viên tự ghi chép các lần mình sử dụng AI và tự commit/push lên repository từ tài khoản GitHub cá nhân tương ứng.
2. **Tính trung thực:** Ghi nhận trung thực, đầy đủ mọi lần tương tác quan trọng có ảnh hưởng đến mã nguồn, bản thiết kế kỹ thuật hoặc kịch bản kiểm thử (test driver/test cases).
3. **Phạm vi ghi nhận:** **CHỈ** ghi các tương tác liên quan trực tiếp đến **CODE, THIẾT KẾ KỸ THUẬT, VÀ KIỂM THỬ**. Không ghi các nội dung phụ trợ như viết README chung chung hay các hướng dẫn thao tác cơ bản về Git/GitHub/SourceTree.
4. **Nắm vững mã nguồn:** Công cụ AI chỉ đóng vai trò trợ lý/cố vấn. Mỗi thành viên phải tự kiểm tra, thẩm định, hiểu tường tận toàn bộ mã nguồn mình nộp và chịu trách nhiệm giải trình trước giảng viên khi được vấn đáp.
5. **Bảo mật thông tin:** Tuyệt đối không lưu trữ email cá nhân, token, mật khẩu hay dữ liệu nhạy cảm vào nhật ký.

---

## 2. Bảng tổng hợp các lần tương tác AI

| # | Ngày | Người thực hiện | Công cụ AI | Mục tiêu chính | File / Phần ảnh hưởng |
| :-: | :---: | :--- | :--- | :--- | :--- |
| **#1** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Định hướng thiết kế & thứ tự thực hiện phần việc Thành viên 1 | Thiết kế kiến trúc tổng thể (chưa gắn file cụ thể) |
| **#2** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Sinh mã nguồn Room, StandardRoom, ServiceRoom và test driver K01 | `Room.java`, `StandardRoom.java`, `ServiceRoom.java`, `PRO192Project.java` |
| **#3** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Khắc phục 3 lỗi biên dịch khi đưa mã nguồn vào NetBeans 17 | `Room.java`, `ServiceRoom.java` |
| **#4** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Hướng dẫn kiểm thử ca K01, K02/K03 (phần tiền phòng) và ca ngoại lệ | `PRO192Project.java` (RoomTestDriver) |
| **#5** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Đề xuất & thống nhất định dạng lưu trữ file văn bản cho dự án | `PRO192Project/data/*.txt`, `docs/DATA_FORMAT.md` |
| **#6** | 2026-10-25 | Thái Quang Huy – QS190001 | Antigravity (Gemini 3.8 Flash) | Khởi tạo 4 file dữ liệu mẫu, fixture K10, tài liệu đặc tả và nhật ký AI | `PRO192Project/data/*.txt`, `PRO192Project/docs-test-data/k10/*`, `docs/DATA_FORMAT.md`, `docs/ai_logs/AI_INTERACTION_LOG.md` |

---

## 3. Chi tiết từng lần tương tác

### Mục #1
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Claude (model Sonnet 5.5, qua giao diện Claude trên web/app)
- **Mục tiêu:** Hiểu rõ yêu cầu nghiệp vụ của Thành viên 1 ("hai loại phòng, tính tiền đa hình, cập nhật giá hiện hành") và xác định thứ tự các bước thực hiện.
- **Prompt chính:** Nhờ AI xem mô tả task của Thành viên 1 trong đề bài môn học PRO192 và hướng dẫn cách thiết kế cấu trúc lớp, thứ tự làm việc.
- **Kết quả AI đưa ra:** 
  - Gợi ý xây dựng lớp trừu tượng `Room` làm cha, 2 lớp con là `StandardRoom` và `ServiceRoom`.
  - Lời gọi đa hình `calculateRoomCharge()` qua biến tham chiếu kiểu `Room` (tuyệt đối không dùng `if-else` hay `switch-case` theo loại phòng).
  - Sử dụng kiểu dữ liệu `long` cho các trường tiền tệ để tránh tràn số.
  - Các hàm kiểm tra tính hợp lệ dữ liệu (validate): sức chứa từ 1 đến 10 người, giá tiền/phí từ 0 đến 10.000.000 VNĐ.
  - Định hướng phát triển tiếp: tạo `PriceConfig` và `RoomService`.
  - Thống nhất trước giao diện API dùng chung với các thành viên khác trong nhóm: `getRoomType()`, `getServiceFee()`, `getBaseRent()`, cùng nhóm hàm quản lý người ở (`addResident`, `removeResident`, `hasResident`, `isFull`, `getVacancy`, `getResidentCount`).
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Đối chiếu các gợi ý thiết kế với các yêu cầu mục 3, mục 4 và mục 6 trong tài liệu đặc tả đề bài môn học PRO192.
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** Bản thiết kế kiến trúc chung (chưa gắn với file code cụ thể).

---

### Mục #2
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Claude (model Sonnet 5.5, qua giao diện Claude trên web/app)
- **Mục tiêu:** Có bản cài đặt ban đầu cho mô hình phòng, đáp ứng các ràng buộc: không dùng từ khóa `final`, mọi thuộc tính đều `private`, sử dụng rõ ràng `this.` và `super.`.
- **Prompt chính:** "không dùng final, viết cả lớp StandardRoom và ServiceRoom cho tôi"
- **Kết quả AI đưa ra:** 
  - Lớp `Room` (abstract): các phương thức trừu tượng `calculateRoomCharge()`, `getRoomType()`, `getServiceFee()`; các hàm kiểm tra hợp lệ `validateRoomId()`, `validateCapacity()`, `validateMoney()`; khung quản lý sinh viên trong phòng: `getResidentCount()`, `getVacancy()`, `isFull()`, `hasResident()`, `addResident()`, `removeResident()`, `getResidentIds()` (trả về bản sao `ArrayList` để đảm bảo tính bao gói).
  - Lớp `StandardRoom`: kế thừa `Room`, tiền phòng bằng `super.getBaseRent()`, phí dịch vụ bằng 0.
  - Lớp `ServiceRoom`: kế thừa `Room`, bổ sung thuộc tính `serviceFee`, `calculateRoomCharge()` bằng `super.getBaseRent() + this.serviceFee`.
  - Mã nguồn kiểm thử `RoomTestDriver`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Chạy test driver trong môi trường NetBeans 17. Kết quả ca K01: phòng A102 tính đúng 2.100.000 VNĐ; 4 ca kiểm tra biên/đầu vào không hợp lệ (mã phòng rỗng, mã phòng có khoảng trắng, sức chứa 11 người, phí dịch vụ vượt 10.000.000 VNĐ) đều bị từ chối thành công bằng `IllegalArgumentException`; dự án biên dịch BUILD SUCCESSFUL. Riêng dòng hiển thị của phòng A101 = 1.200.000 VNĐ chưa thấy rõ trong ảnh chụp Output, cần xác nhận: (điền: xác nhận lại).
- **Phần đã tự chỉnh sửa:** Bổ sung khai báo `package pro192project;` vào đầu các file; tích hợp mã nguồn kiểm thử vào trực tiếp hàm `main` của file `PRO192Project.java`.
- **File bị ảnh hưởng:** `Room.java`, `StandardRoom.java`, `ServiceRoom.java`, `PRO192Project.java`.

---

### Mục #3
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Claude (model Sonnet 5.5, qua giao diện Claude trên web/app)
- **Mục tiêu:** Xử lý triệt để 3 lỗi biên dịch báo đỏ xuất hiện khi đưa mã nguồn vào NetBeans 17.
- **Prompt chính:** Gửi ảnh chụp màn hình thông báo lỗi biên dịch trong NetBeans 17 và nhờ AI phân tích nguyên nhân cùng giải pháp khắc phục.
- **Kết quả AI đưa ra:** 
  - *Lỗi 1:* `cannot find symbol: validateCapacity(int)` -> Do lúc sao chép file `Room.java` mới chỉ dán nửa đầu, thiếu các phương thức `validate...` ở cuối; cách sửa: sao chép đầy đủ toàn bộ nội dung file.
  - *Lỗi 2:* `cannot find symbol: variable size` -> Do viết nhầm cú pháp thuộc tính `this.residentIds.size;` thay vì gọi phương thức `size()`; ngoài ra phần sau hàm `getResidentCount()` bị thiếu; cách sửa: đổi thành `size()` và dán đầy đủ các phương thức còn lại (`getVacancy`, `isFull`, `hasResident`, `addResident`, `removeResident`, `getResidentIds`, `validateRoomId`, `validateCapacity`, `validateMoney`, `toString`).
  - *Lỗi 3:* `variable serviceFee is already defined in constructor ServiceRoom(long,String,int,long,long)` -> Do khai báo lặp/thừa tham số trong constructor; cách sửa: chuẩn hóa chỉ dùng một constructor 4 tham số duy nhất `ServiceRoom(String roomId, int capacity, long baseRent, long serviceFee)`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Thực hiện Clean and Build trên NetBeans 17 thành công (BUILD SUCCESSFUL, 0 lỗi biên dịch), test driver chạy trơn tru.
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** `Room.java`, `ServiceRoom.java`.

---

### Mục #4
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Claude (model Sonnet 5.5, qua giao diện Claude trên web/app)
- **Mục tiêu:** Nắm vững phương pháp chạy kiểm thử đơn vị và cách điền kết quả thực tế vào bảng kịch bản kiểm thử trong `README.md`.
- **Prompt chính:** Nhờ AI hướng dẫn chạy kiểm thử các ca K01, kiểm tra phần tiền phòng của K02/K03 và các ca dữ liệu biên/sai định dạng.
- **Kết quả AI đưa ra:** 
  - Hướng dẫn thiết lập và chạy `RoomTestDriver` trong NetBeans.
  - Kỳ vọng kiểm thử K01: A101 (Standard) = 1.200.000 VNĐ, A102 (Service) = 2.100.000 VNĐ.
  - Kỳ vọng thành phần tiền phòng của K02/K03: tiền phòng tương ứng là 1.200.000 VNĐ và 2.100.000 VNĐ; khi cộng tiền điện (150.000 VNĐ) và nước (150.000 VNĐ) thì tổng hóa đơn sẽ là 1.500.000 VNĐ và 2.400.000 VNĐ.
  - Gợi ý bổ sung các ca kiểm thử biên nghiêm ngặt: sức chứa bằng 0, bằng 1, bằng 10; tiền phòng âm; tiền phòng chạm ngưỡng tối đa 10.000.000 VNĐ; kiểm tra hàm `setBaseRent(10_000_001L)` phải ném ngoại lệ.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** (điền: ca nào đã thực sự chạy)
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** `PRO192Project.java` (hoặc `RoomTestDriver`).

---

### Mục #5
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Claude (model Sonnet 5.5, qua giao diện Claude trên web/app)
- **Mục tiêu:** Lựa chọn phương án lưu trữ dữ liệu tối ưu cho phần việc của Thành viên 5 (nhập/xuất file).
- **Prompt chính:** Nhờ AI so sánh các giải pháp lưu trữ dữ liệu (Text file vs. Java Serialization) cho bài tập lớn Java Core và đề xuất cấu trúc cụ thể.
- **Kết quả AI đưa ra:** 
  - Phân tích ưu nhược điểm giữa file văn bản UTF-8 và cơ chế tuần tự hóa `Serializable`. Ưu tiên chọn file văn bản vì dễ chấm bài, dễ tạo dữ liệu kiểm thử độc lập và kiểm tra trực quan.
  - Đề xuất kiến trúc 4 file đặt trong thư mục `data/` (`rooms.txt`, `students.txt`, `prices.txt`, `bills.txt`).
  - Dùng dấu gạch đứng `|` làm ký tự phân tách trường.
  - Tích hợp thông tin xếp phòng trực tiếp vào cột thứ 3 của `students.txt` để tinh gọn hệ thống.
  - Đề xuất các nguyên tắc an toàn: lưu ngay sau mỗi thao tác thành công, ghi qua file trung gian `.tmp` rồi mới hoán đổi, ném lỗi kèm số dòng khi file hỏng (không âm thầm bỏ qua), tự động sinh mã hóa đơn mới theo công thức $\max + 1$.
  - Soạn thảo khung nội dung các file mẫu và tài liệu đặc tả `docs/DATA_FORMAT.md`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** (điền: nhóm đã thống nhất chưa)
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** `PRO192Project/data/*.txt`, `docs/DATA_FORMAT.md`.

---

### Mục #6
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Antigravity (model Gemini 3.8 Flash, qua Antigravity agentic coding assistant)
- **Mục tiêu:** Tự động hóa việc tạo 4 file dữ liệu mẫu trong `data/`, bộ dữ liệu fixture K10 trong `docs-test-data/k10/`, tài liệu quy định định dạng `docs/DATA_FORMAT.md`, và lập file nhật ký sử dụng AI này; sau đó tự viết kịch bản kiểm tra tự động xác thực toàn bộ tính toàn vẹn của dữ liệu.
- **Prompt chính:** Thực hiện Việc 1 (tạo 4 file dữ liệu mẫu UTF-8 tại `PRO192Project/data/`, bộ fixture K10 tại `PRO192Project/docs-test-data/k10/`, tài liệu `docs/DATA_FORMAT.md`, chạy script tự kiểm tra không để lại file thừa) và Việc 2 (viết `docs/ai_logs/AI_INTERACTION_LOG.md` theo cấu trúc chuẩn, đối chiếu mã nguồn và lịch sử git thực tế của dự án).
- **Kết quả AI đưa ra:** 
  - Tạo thành công 4 file dữ liệu mẫu: `PRO192Project/data/rooms.txt`, `PRO192Project/data/students.txt`, `PRO192Project/data/prices.txt`, `PRO192Project/data/bills.txt` (file rỗng 0 bytes).
  - Tạo bộ fixture K10 tại `PRO192Project/docs-test-data/k10/` với 2 hóa đơn mẫu (HD001 đã trả 1.500.000 VNĐ, HD002 còn nợ 2.400.000 VNĐ).
  - Soạn thảo hoàn chỉnh tài liệu đặc tả quy tắc đọc/ghi và định dạng dữ liệu `docs/DATA_FORMAT.md`.
  - Viết file nhật ký `docs/ai_logs/AI_INTERACTION_LOG.md`.
  - Viết và thực thi kịch bản kiểm thử dữ liệu: xác nhận 100% dòng dữ liệu chuẩn số trường, mã không chứa khoảng trắng, sinh viên gắn đúng phòng và không vượt quá sức chứa, fixture K10 tính đúng tổng nợ 2.400.000 VNĐ, tổng đã trả 1.500.000 VNĐ và mã tiếp theo là HD003.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Kết quả thực tế từ kịch bản kiểm tra:
  ```text
  KIEM TRA DU LIEU TAI: PRO192Project/data
  - rooms.txt (2 dong): A101 (STANDARD, cap: 2), A102 (SERVICE, cap: 3) -> HOP LE
  - students.txt (4 dong): 4 sinh vien, phong hop le -> HOP LE
  - Suc chua: A101 (2/2 sv), A102 (1/3 sv) -> Dat tieu chuan
  - prices.txt (1 dong, 5 truong) -> HOP LE
  - bills.txt: File RONG (0 bytes) -> DUNG YEU CAU

  KIEM TRA DU LIEU TAI: PRO192Project/docs-test-data/k10
  - rooms.txt, students.txt, prices.txt -> HOP LE
  - bills.txt (2 hoa don HD001, HD002):
    + Tong da tra: 1.500.000 VND (Kỳ vọng: 1.500.000 VND)
    + Tong con no: 2.400.000 VND (Kỳ vọng: 2.400.000 VND)
    + Ma hoa don ke tiep: HD003 (Kỳ vọng: HD003)
  -> KET QUA: PASS 100%
  ```
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** `PRO192Project/data/rooms.txt`, `PRO192Project/data/students.txt`, `PRO192Project/data/prices.txt`, `PRO192Project/data/bills.txt`, `PRO192Project/docs-test-data/k10/*`, `docs/DATA_FORMAT.md`, `docs/ai_logs/AI_INTERACTION_LOG.md`.

---

## 4. Mẫu nhật ký (Template dành cho các thành viên bổ sung)

Khi bất kỳ thành viên nào trong nhóm sử dụng AI để hỗ trợ công việc liên quan đến mã nguồn hoặc kiểm thử, hãy copy biểu mẫu dưới đây, điền đầy đủ thông tin và dán tiếp vào cuối tài liệu này:

```markdown
### Mục #<Số thứ tự>
- **Ngày:** YYYY-MM-DD
- **Người thực hiện:** <Họ và tên> – <MSSV>
- **Công cụ AI:** <Tên công cụ & Phiên bản/Model, ví dụ: Claude 3.5 Sonnet, ChatGPT GPT-4o, Antigravity>
- **Mục tiêu:** <Mục tiêu cụ thể cần AI giải quyết>
- **Prompt chính:** <Câu lệnh/yêu cầu chính gửi cho AI>
- **Kết quả AI đưa ra:** <Tóm tắt những gì AI đã phản hồi/sinh mã/gợi ý>
- **Link / trích đoạn:** <Link chia sẻ cuộc hội thoại hoặc trích đoạn prompt quan trọng>
- **Phần đã kiểm tra:** <Cách thức bạn kiểm tra tính đúng đắn của code do AI sinh>
- **Phần đã tự chỉnh sửa:** <Những điểm bạn tự tay sửa lại so với phản hồi gốc của AI>
- **File bị ảnh hưởng:** <Danh sách các file trong project bị thay đổi hoặc tạo mới>
```
