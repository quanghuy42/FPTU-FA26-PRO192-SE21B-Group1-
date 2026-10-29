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

## 2. Bảng tổng hợp các lần tương tác AI của cả 5 thành viên

| # | Ngày | Người thực hiện | Công cụ AI | Mục tiêu chính | File / Phần ảnh hưởng |
| :-: | :---: | :--- | :--- | :--- | :--- |
| **#1** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Định hướng thiết kế & thứ tự thực hiện phần việc Thành viên 1 | Thiết kế kiến trúc tổng thể |
| **#2** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Sinh mã nguồn Room, StandardRoom, ServiceRoom và test driver K01 | `Room.java`, `StandardRoom.java`, `ServiceRoom.java`, `PRO192Project.java` |
| **#3** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Khắc phục 3 lỗi biên dịch khi đưa mã nguồn vào NetBeans 17 | `Room.java`, `ServiceRoom.java` |
| **#4** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Hướng dẫn kiểm thử ca K01, K02/K03 (phần tiền phòng) và ca ngoại lệ | `PRO192Project.java` (RoomTestDriver) |
| **#5** | 2026-10-25 | Thái Quang Huy – QS190001 | Claude (Sonnet 5.5) | Đề xuất & thống nhất định dạng lưu trữ file văn bản cho dự án | `PRO192Project/data/*.txt`, `docs/DATA_FORMAT.md` |
| **#6** | 2026-10-25 | Thái Quang Huy – QS190001 | Antigravity (Gemini 3.8 Flash) | Khởi tạo 4 file dữ liệu mẫu, fixture K10, tài liệu đặc tả và nhật ký AI | `PRO192Project/data/*.txt`, `PRO192Project/docs-test-data/k10/*`, `docs/DATA_FORMAT.md`, `docs/ai_logs/AI_INTERACTION_LOG.md` |
| **#7** | 2026-10-25 | Lê Thành Luân – QE200043 | ChatGPT (GPT-4o) | Xây dựng phần quản lý hồ sơ sinh viên (`Student.java`) | `Student.java` |
| **#8** | 2026-10-25 | Lê Thành Luân – QE200043 | ChatGPT (GPT-4o) | Xây dựng lớp quản lý sinh viên (`StudentManager.java`) | `StudentManager.java` |
| **#9** | 2026-10-25 | Lê Thành Luân – QE200043 | ChatGPT (GPT-4o) | Tạo test driver cho nghiệp vụ sinh viên | `StudentManagerTest.java` / Test Driver |
| **#10** | 2026-10-25 | Lê Thành Luân – QE200043 | ChatGPT (GPT-4o) | Khắc phục lỗi tương thích API giữa `StudentManager` và `Room` | `StudentManager.java` |
| **#11** | 2026-10-27 | Hồ Trương Anh Quân – QE210067 | Gemini (3 Flash) | Định hướng thiết kế & kiểm tra dữ liệu cho Thành viên 3 (`BillManager`, `PriceSnapshot`) | `BillManager.java`, `PriceSnapshot.java`, `Bill.java` |
| **#12** | 2026-10-27 | Hồ Trương Anh Quân – QE210067 | Gemini (3 Flash) | Khắc phục lỗi `cannot find symbol` và `Unclosed group` | `BillManager.java`, `Main.java` |
| **#13** | 2026-10-27 | Hồ Trương Anh Quân – QE210067 | Gemini (3 Flash) | Xây dựng test driver nhập liệu bằng `Scanner` và sửa lỗi luồng `try-catch` | `Main.java` (Test Driver) |
| **#14** | 2026-10-26 | Nguyễn Đăng Huy – QE210182 | Gemini (3 Flash) | Thiết kế thực thể `Bill`, `PriceSnapshot` và dịch vụ thanh toán/tra cứu nợ | `Bill.java`, `PriceSnapshot.java`, `BillService.java` |
| **#15** | 2026-10-26 | Nguyễn Đăng Huy – QE210182 | Gemini (3 Flash) | Tối ưu hóa các phương thức tính toán nợ và chi tiết hóa đơn | `BillService.java` |
| **#16** | 2026-10-26 | Nguyễn Đăng Huy – QE210182 | Gemini (3 Flash) | Xử lý kiểm tra trạng thái thanh toán và chặn thu tiền trùng lặp | `BillService.java` |
| **#17** | 2026-10-27 | Lê Trần Khải Nguyên – QE210233 | ChatGPT & Claude | Thiết kế 4 lớp đọc/ghi file an toàn (phòng, SV, giá, hóa đơn) theo `DATA_FORMAT.md` | `StorageStudent.java`, `StorageRoom.java`, `StoragePrice.java`, `StorageBill.java` |
| **#18** | 2026-10-27 | Lê Trần Khải Nguyên – QE210233 | Claude (Sonnet 5.5) | Đổi tên 4 lớp `Storage...` tránh xung đột và chuẩn hóa package `pro192project` | `StorageStudent.java`, `StorageRoom.java`, `StoragePrice.java`, `StorageBill.java` |

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
  - Thống nhất trước giao diện API dùng chung: `getRoomType()`, `getServiceFee()`, `getBaseRent()`, cùng nhóm hàm quản lý người ở (`addResident`, `removeResident`, `hasResident`, `isFull`, `getVacancy`, `getResidentCount`).
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Đối chiếu các gợi ý thiết kế với các yêu cầu mục 3, mục 4 và mục 6 trong tài liệu đặc tả đề bài môn học PRO192.
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** Bản thiết kế kiến trúc chung.

---

### Mục #2
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Claude (model Sonnet 5.5, qua giao diện Claude trên web/app)
- **Mục tiêu:** Có bản cài đặt ban đầu cho mô hình phòng, đáp ứng các ràng buộc: không dùng từ khóa `final`, mọi thuộc tính đều `private`, sử dụng rõ ràng `this.` và `super.`.
- **Prompt chính:** "không dùng final, viết cả lớp StandardRoom và ServiceRoom cho tôi"
- **Kết quả AI đưa ra:** Lớp `Room` (abstract), `StandardRoom`, `ServiceRoom`, mã nguồn kiểm thử `RoomTestDriver`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Chạy test driver trong môi trường NetBeans 17. Kết quả ca K01: phòng A102 tính đúng 2.100.000 VNĐ; 4 ca kiểm tra biên/đầu vào không hợp lệ đều bị từ chối thành công bằng `IllegalArgumentException`; dự án biên dịch BUILD SUCCESSFUL.
- **Phần đã tự chỉnh sửa:** Bổ sung khai báo `package pro192project;` vào đầu các file; tích hợp mã nguồn kiểm thử vào trực tiếp hàm `main` của file `PRO192Project.java`.
- **File bị ảnh hưởng:** `Room.java`, `StandardRoom.java`, `ServiceRoom.java`, `PRO192Project.java`.

---

### Mục #3
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Claude (model Sonnet 5.5, qua giao diện Claude trên web/app)
- **Mục tiêu:** Xử lý triệt để 3 lỗi biên dịch báo đỏ xuất hiện khi đưa mã nguồn vào NetBeans 17.
- **Prompt chính:** Gửi ảnh chụp màn hình thông báo lỗi biên dịch trong NetBeans 17 và nhờ AI phân tích nguyên nhân cùng giải pháp khắc phục.
- **Kết quả AI đưa ra:** Sửa lỗi thiếu hàm `validateCapacity`, sửa `this.residentIds.size` thành `size()`, chuẩn hóa constructor 4 tham số duy nhất của `ServiceRoom`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Thực hiện Clean and Build trên NetBeans 17 thành công (BUILD SUCCESSFUL, 0 lỗi biên dịch).
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** `Room.java`, `ServiceRoom.java`.

---

### Mục #4
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Claude (model Sonnet 5.5, qua giao diện Claude trên web/app)
- **Mục tiêu:** Nắm vững phương pháp chạy kiểm thử đơn vị và cách điền kết quả thực tế vào bảng kịch bản kiểm thử trong `README.md`.
- **Prompt chính:** Nhờ AI hướng dẫn chạy kiểm thử các ca K01, kiểm tra phần tiền phòng của K02/K03 và các ca dữ liệu biên/sai định dạng.
- **Kết quả AI đưa ra:** Hướng dẫn thiết lập và chạy `RoomTestDriver` trong NetBeans; kỳ vọng kiểm thử K01, K02/K03; gợi ý các ca biên.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** (điền: ca nào đã thực sự chạy)
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** `PRO192Project.java`.

---

### Mục #5
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Claude (model Sonnet 5.5, qua giao diện Claude trên web/app)
- **Mục tiêu:** Lựa chọn phương án lưu trữ dữ liệu tối ưu cho phần việc của Thành viên 5 (nhập/xuất file).
- **Prompt chính:** Nhờ AI so sánh các giải pháp lưu trữ dữ liệu (Text file vs. Java Serialization) và đề xuất cấu trúc cụ thể.
- **Kết quả AI đưa ra:** Đề xuất dùng file văn bản UTF-8 phân cách bằng dấu `|`, 4 file trong `data/`, lưu an toàn qua file trung gian `.tmp`, sinh mã hóa đơn $\max + 1$, soạn tài liệu `DATA_FORMAT.md`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** (điền: nhóm đã thống nhất chưa)
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** `PRO192Project/data/*.txt`, `docs/DATA_FORMAT.md`.

---

### Mục #6
- **Ngày:** 2026-10-25
- **Người thực hiện:** Thái Quang Huy – QS190001
- **Công cụ AI:** Antigravity (model Gemini 3.8 Flash, qua Antigravity agentic coding assistant)
- **Mục tiêu:** Tự động hóa việc tạo 4 file dữ liệu mẫu trong `data/`, bộ dữ liệu fixture K10 trong `docs-test-data/k10/`, tài liệu quy định định dạng `docs/DATA_FORMAT.md`, và lập file nhật ký sử dụng AI; sau đó tự viết kịch bản kiểm tra tự động xác thực tính toàn vẹn của dữ liệu.
- **Prompt chính:** Thực hiện Việc 1 (tạo 4 file dữ liệu mẫu UTF-8 tại `PRO192Project/data/`, bộ fixture K10 tại `PRO192Project/docs-test-data/k10/`, tài liệu `docs/DATA_FORMAT.md`) và Việc 2 (viết nhật ký AI).
- **Kết quả AI đưa ra:** Tạo thành công 4 file mẫu, fixture K10, tài liệu `DATA_FORMAT.md`, chạy script kiểm tra PASS 100%.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Script kiểm tra tự động xác nhận toàn bộ ràng buộc đều PASS.
- **Phần đã tự chỉnh sửa:** (điền)
- **File bị ảnh hưởng:** `PRO192Project/data/*`, `PRO192Project/docs-test-data/k10/*`, `docs/DATA_FORMAT.md`, `docs/ai_logs/AI_INTERACTION_LOG.md`.

---

### Mục #7
- **Ngày:** 2026-10-25
- **Người thực hiện:** Lê Thành Luân – QE200043
- **Công cụ AI:** ChatGPT (GPT-4o)
- **Mục tiêu:** Xây dựng phần quản lý hồ sơ sinh viên cho hệ thống quản lý ký túc xá, bao gồm thông tin sinh viên, mã sinh viên và trạng thái đã được xếp phòng hay chưa.
- **Prompt chính:** Yêu cầu AI hỗ trợ thiết kế và viết lớp `Student.java` theo yêu cầu của bài PRO192, sử dụng Java và đóng gói dữ liệu bằng các thuộc tính cùng phương thức phù hợp.
- **Kết quả AI đưa ra:** Đề xuất lớp `Student` lưu mã sinh viên, họ tên và mã phòng hiện tại; cung cấp các phương thức truy vấn trạng thái xếp phòng, gán phòng và trả phòng. Logic ngăn dữ liệu đầu vào không hợp lệ và giữ trạng thái sinh viên nhất quán.
- **Link / trích đoạn:** Trích đoạn tương tác về phần lớp `Student.java` trong lịch sử ChatGPT của Luân.
- **Phần đã kiểm tra:** Đối chiếu tên thuộc tính và phương thức với yêu cầu của phần quản lý sinh viên; kiểm tra lại bằng cách biên dịch trong project tích hợp.
- **Phần đã tự chỉnh sửa:** Rà soát tên package, chữ ký phương thức và cách gọi từ lớp quản lý để phù hợp với cấu trúc project nhóm.
- **File bị ảnh hưởng:** `Student.java`.

---

### Mục #8
- **Ngày:** 2026-10-25
- **Người thực hiện:** Lê Thành Luân – QE200043
- **Công cụ AI:** ChatGPT (GPT-4o)
- **Mục tiêu:** Xây dựng lớp quản lý sinh viên và các nghiệp vụ thêm, tìm kiếm, xếp phòng và trả phòng.
- **Prompt chính:** Yêu cầu AI hỗ trợ triển khai `StudentManager.java`, quản lý danh sách sinh viên và kết nối thông tin sinh viên với các phòng hiện có.
- **Kết quả AI đưa ra:** Đề xuất dùng danh sách sinh viên và bản đồ phòng để tra cứu theo mã; kiểm tra mã sinh viên trùng trước khi thêm; kiểm tra sinh viên và phòng tồn tại trước khi xếp phòng; cập nhật trạng thái sinh viên và thông tin người ở trong phòng khi xếp hoặc trả phòng.
- **Link / trích đoạn:** Trích đoạn tương tác về phần lớp `StudentManager.java` trong lịch sử ChatGPT của Luân.
- **Phần đã kiểm tra:** Đối chiếu luồng xử lý với các trường hợp thêm sinh viên, tìm kiếm theo mã, xếp phòng và trả phòng. Cần biên dịch cùng `Room.java` và xác nhận tên API thực tế của lớp `Room`.
- **Phần đã tự chỉnh sửa:** Điều chỉnh khai báo package và rà soát lời gọi API `Room`.
- **File bị ảnh hưởng:** `StudentManager.java`.

---

### Mục #9
- **Ngày:** 2026-10-25
- **Người thực hiện:** Lê Thành Luân – QE200043
- **Công cụ AI:** ChatGPT (GPT-4o)
- **Mục tiêu:** Chuẩn bị ca kiểm thử cho phần quản lý sinh viên và xếp phòng, kiểm tra logic thêm sinh viên, xếp phòng hợp lệ và từ chối khi phòng đầy.
- **Prompt chính:** Yêu cầu AI hướng dẫn viết test driver hoặc các bước kiểm tra cho `StudentManager.java`, bao gồm thêm sinh viên, gán phòng còn chỗ và thử gán phòng đã đủ sức chứa.
- **Kết quả AI đưa ra:** Gợi ý kịch bản kiểm thử: tạo phòng với sức chứa xác định, thêm sinh viên, gán sinh viên vào phòng đến khi đầy rồi kiểm tra lần gán tiếp theo bị từ chối; đồng thời kiểm tra trường hợp sinh viên đã có phòng không được gán tiếp phòng khác.
- **Link / trích đoạn:** Trích đoạn tương tác về kịch bản kiểm thử cho sinh viên trong lịch sử ChatGPT của Luân.
- **Phần đã kiểm tra:** Chạy thử kịch bản thêm sinh viên và xếp phòng; đối chiếu với các trường hợp K04, K05 trong bảng kiểm thử của đề tài.
- **Phần đã tự chỉnh sửa:** Điều chỉnh dữ liệu mẫu dùng trong test driver để khớp với mã phòng và mã sinh viên theo quy ước của nhóm.
- **File bị ảnh hưởng:** Mã nguồn kiểm thử `StudentManager`.

---

### Mục #10
- **Ngày:** 2026-10-25
- **Người thực hiện:** Lê Thành Luân – QE200043
- **Công cụ AI:** ChatGPT (GPT-4o)
- **Mục tiêu:** Khắc phục lỗi không tìm thấy phương thức hoặc không khớp kiểu dữ liệu khi ghép `StudentManager.java` với lớp `Room` trong branch tích hợp.
- **Prompt chính:** Gửi thông báo lỗi biên dịch giữa `StudentManager` và `Room` (các phương thức kiểm tra sức chứa và thêm sinh viên vào phòng) và nhờ AI giải thích nguyên nhân cùng cách sửa.
- **Kết quả AI đưa ra:** Giải thích sự khác biệt giữa tên phương thức trong bản nháp của `StudentManager` và API thực tế của lớp `Room` (ví dụ `addResident` so với `addStudent`); đề xuất gọi đúng phương thức mà `Room` cung cấp hoặc thống nhất lại chữ ký phương thức với thành viên phụ trách lớp `Room`.
- **Link / trích đoạn:** Trích đoạn tương tác về lỗi tương thích API trong lịch sử ChatGPT của Luân.
- **Phần đã kiểm tra:** Sửa lời gọi phương thức trong `StudentManager.java` và biên dịch lại cùng `Room.java`; xác nhận không còn lỗi "cannot find symbol".
- **Phần đã tự chỉnh sửa:** Cập nhật trực tiếp mã nguồn `StudentManager.java` để dùng đúng tên phương thức của lớp `Room`.
- **File bị ảnh hưởng:** `StudentManager.java`.

---

### Mục #11
- **Ngày:** 2026-10-27
- **Người thực hiện:** Hồ Trương Anh Quân – QE210067
- **Công cụ AI:** Gemini (model Gemini 3 Flash, qua giao diện Gemini trên web)
- **Mục tiêu:** Hiểu rõ nghiệp vụ lập hóa đơn của Thành viên 3 ("nhập chỉ số/tháng, lập hóa đơn và bản chụp giá") và thiết kế cấu trúc lớp `BillManager`, `PriceSnapshot` đáp ứng các yêu cầu kiểm tra dữ liệu đầu vào.
- **Prompt chính:** "Hướng dẫn tôi thiết kế lớp BillManager và PriceSnapshot trong Java cho bài toán lập hóa đơn ký túc xá, cần kiểm tra phòng có người, định dạng tháng YYYY-MM, chỉ số điện nước cuối >= đầu."
- **Kết quả AI đưa ra:** Định hướng thiết kế lớp `PriceSnapshot` để lưu lại đơn giá điện/nước tại thời điểm chốt hóa đơn. Xây dựng phương thức `createBill(...)` trong `BillManager`, kiểm tra hợp lệ phòng có người ở, tháng `YYYY-MM`, chỉ số điện nước cuối $\ge$ đầu, không trùng cặp `roomId` và `billingMonth`.
- **Link / trích đoạn:** https://gemini.google.com/app/c73a338a21afdfcb
- **Phần đã kiểm tra:** Đối chiếu các điều kiện ràng buộc với yêu cầu của các ca kiểm thử K02, K03, K06, K07 trong tài liệu đặc tả đề bài môn học PRO192.
- **Phần đã tự chỉnh sửa:** Điều chỉnh các thông điệp ngoại lệ (Exception message) để thống nhất cách hiển thị tiếng Việt với toàn bộ dự án.
- **File bị ảnh hưởng:** `BillManager.java`, `PriceSnapshot.java`, `Bill.java`.

---

### Mục #12
- **Ngày:** 2026-10-27
- **Người thực hiện:** Hồ Trương Anh Quân – QE210067
- **Công cụ AI:** Gemini (model Gemini 3 Flash, qua giao diện Gemini trên web)
- **Mục tiêu:** Xử lý các lỗi báo đỏ xuất hiện trên NetBeans 17 khi dùng `Scanner` nhập dữ liệu: `cannot find symbol` và lỗi biểu thức chính quy `Unclosed group`.
- **Prompt chính:** "Sửa lỗi biên dịch NetBeans cannot find symbol và lỗi Unclosed group trong Java khi dùng Scanner nhập dữ liệu."
- **Kết quả AI đưa ra:** Chuẩn hóa lại biểu thức chính quy kiểm tra tháng; loại bỏ các ký tự nhãn tham số thừa do NetBeans tự động hiển thị.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Thực hiện Clean and Build trên NetBeans 17 thành công (BUILD SUCCESSFUL, 0 lỗi biên dịch).
- **Phần đã tự chỉnh sửa:** Xóa hoàn toàn các chuỗi nhãn tham số thừa trong mã nguồn.
- **File bị ảnh hưởng:** `BillManager.java`.

---

### Mục #13
- **Ngày:** 2026-10-27
- **Người thực hiện:** Hồ Trương Anh Quân – QE210067
- **Công cụ AI:** Gemini (model Gemini 3 Flash, qua giao diện Gemini trên web)
- **Mục tiêu:** Xây dựng hàm `main` nhập liệu tương tác qua `Scanner` để kiểm thử thực tế, đồng thời sửa lỗi chương trình vẫn in dòng `=> [THANH CONG]` dù đã bắt được ngoại lệ.
- **Prompt chính:** "Code main của tôi bị lỗi khi nhập sai nó nhảy vào catch nhưng sau đó vẫn in ra [THANH CONG] ở cuối, hãy sửa giúp tôi."
- **Kết quả AI đưa ra:** Tái cấu trúc khối `try-catch-finally` trong hàm `main`, đặt lời gọi `createBill` và câu lệnh in thông báo thành công liền kề nhau trong cùng một khối `try`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Chạy `Run File` trên NetBeans 17 với các dữ liệu sai. Console báo lỗi đúng nghiệp vụ và không còn in nhầm trạng thái thành công.
- **Phần đã tự chỉnh sửa:** Chuẩn hóa định dạng hiển thị thông báo trên console.
- **File bị ảnh hưởng:** `BillManager.java`, Test Driver.

---

### Mục #14
- **Ngày:** 2026-10-26
- **Người thực hiện:** Nguyễn Đăng Huy – QE210182
- **Công cụ AI:** Gemini (model Gemini 3 Flash)
- **Mục tiêu:** Thiết kế thực thể `Bill`, `PriceSnapshot` và lớp dịch vụ `BillService` đáp ứng các nghiệp vụ: xem chi tiết hóa đơn, xác nhận thanh toán, lọc hóa đơn còn nợ và cộng tổng nợ.
- **Prompt chính:** "Hướng dẫn viết lớp BillService quản lý hóa đơn trong Java: tra cứu hóa đơn theo mã, thanh toán hóa đơn, liệt kê hóa đơn nợ và tính tổng nợ."
- **Kết quả AI đưa ra:** Đề xuất lớp `Bill` với đầy đủ thuộc tính và hàm tính toán: `getElectricityUsage()`, `getWaterUsage()`, `getElectricityCost()`, `getWaterCost()`, `getTotalAmount()`, `markAsPaid()`. Lớp `BillService` chứa danh sách hóa đơn và các phương thức `viewBillDetails`, `payBill`, `listUnpaidBillsAndTotalDebt`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Đối chiếu với các ca nghiệm thu K08, K09 trong đề bài: thanh toán thành công, kiểm tra trả lại không thu thêm tiền, tính tổng nợ chính xác.
- **Phần đã tự chỉnh sửa:** Định dạng hiển thị bảng danh sách nợ trên console cho ngay ngắn, rõ ràng.
- **File bị ảnh hưởng:** `Bill.java`, `PriceSnapshot.java`, `BillService.java`.

---

### Mục #15
- **Ngày:** 2026-10-26
- **Người thực hiện:** Nguyễn Đăng Huy – QE210182
- **Công cụ AI:** Gemini (model Gemini 3 Flash)
- **Mục tiêu:** Tối ưu hóa logic tính toán tiền phòng theo loại phòng `STANDARD` và `SERVICE` trong hóa đơn để tránh nhầm lẫn phí dịch vụ.
- **Prompt chính:** "Cách tính tổng tiền hóa đơn có kiểm tra loại phòng STANDARD và SERVICE dựa vào PriceSnapshot đã chụp."
- **Kết quả AI đưa ra:** Hàm `getRoomCharge()` trong `Bill` kiểm tra nếu `roomType` là `SERVICE` thì cộng thêm `serviceFee`, nếu `STANDARD` thì chỉ lấy `roomPrice`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Tính toán thử với phòng A101 (1.200.000đ) và A102 (1.800.000đ + 300.000đ = 2.100.000đ), kết quả khớp 100%.
- **Phần đã tự chỉnh sửa:** Tự viết lại hàm bằng cách gọi tường minh `this.`.
- **File bị ảnh hưởng:** `Bill.java`.

---

### Mục #16
- **Ngày:** 2026-10-26
- **Người thực hiện:** Nguyễn Đăng Huy – QE210182
- **Công cụ AI:** Gemini (model Gemini 3 Flash)
- **Mục tiêu:** Xử lý nghiệp vụ thanh toán một lần và chặn việc thanh toán lần 2 bị thu thêm tiền (ca K08).
- **Prompt chính:** "Hàm payBill kiểm tra hóa đơn đã thanh toán thì in thông báo đã trả và không thay đổi trạng thái hay số tiền."
- **Kết quả AI đưa ra:** Thêm kiểm tra `targetBill.isPaid()` trước khi gọi `markAsPaid()`. Nếu đã thanh toán, in thông báo rõ ràng và trả về kết quả không thu tiền thêm.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Chạy thử ca K08: thanh toán A101 lần 1 báo thành công, lần 2 báo đã thanh toán trước đó, tổng nợ không bị trừ 2 lần.
- **Phần đã tự chỉnh sửa:** Tối ưu thông báo tiếng Việt trên console.
- **File bị ảnh hưởng:** `BillService.java`.

---

### Mục #17
- **Ngày:** 2026-10-27
- **Người thực hiện:** Lê Trần Khải Nguyên – QE210233
- **Công cụ AI:** ChatGPT & Claude
- **Mục tiêu:** Viết 4 lớp đọc/ghi file văn bản UTF-8 trong thư mục `data/` (`StorageRoom`, `StorageStudent`, `StoragePrice`, `StorageBill`) tuân thủ nghiêm ngặt quy định tại `docs/DATA_FORMAT.md`.
- **Prompt chính:** "Viết các lớp đọc ghi file trong Java bằng Files.readAllLines và Files.write, ghi ra file .tmp rồi mới rename đè lên file thật, báo lỗi kèm số dòng nếu file hỏng."
- **Kết quả AI đưa ra:** Cài đặt 4 lớp tương ứng với 4 file dữ liệu; cơ chế ghi file an toàn (Atomic File Replacement) qua file tạm `.tmp`; kiểm tra định dạng và ném `IOException` kèm tên file và số dòng khi dữ liệu sai lệch. Hàm `nextBillId(...)` tự động tìm mã lớn nhất và cộng 1.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Đối chiếu với ca K10 (lưu -> thoát -> mở lại nạp đủ dữ liệu, mã hóa đơn tiếp theo tự động là `HD003`).
- **Phần đã tự chỉnh sửa:** Tinh chỉnh các thông báo lỗi và bám sát từng cột dữ liệu trong `DATA_FORMAT.md`.
- **File bị ảnh hưởng:** `StorageRoom.java`, `StorageStudent.java`, `StoragePrice.java`, `StorageBill.java`.

---

### Mục #18
- **Ngày:** 2026-10-27
- **Người thực hiện:** Lê Trần Khải Nguyên – QE210233
- **Công cụ AI:** Claude (model Sonnet 5.5)
- **Mục tiêu:** Đổi tên 4 lớp lưu trữ thành tiền tố `Storage...` để tránh trùng tên với các thực thể chính của nhóm, và chuyển dòng package thành `package pro192project;`.
- **Prompt chính:** "Đổi tên các class lưu trữ thành StorageStudent, StorageRoom, StoragePrice, StorageBill và chuyển sang package pro192project."
- **Kết quả AI đưa ra:** Cập nhật tên lớp và constructor tương ứng, đổi khai báo package thành `package pro192project;`.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Biên dịch kiểm tra trong NetBeans 17 thành công.
- **Phần đã tự chỉnh sửa:** Tự tay chuyển các file vào đúng thư mục dự án và kiểm tra lại dòng package.
- **File bị ảnh hưởng:** `StorageRoom.java`, `StorageStudent.java`, `StoragePrice.java`, `StorageBill.java`.

---

## 4. Mẫu nhật ký (Template dành cho các thành viên bổ sung)

```markdown
### Mục #<Số thứ tự>
- **Ngày:** YYYY-MM-DD
- **Người thực hiện:** <Họ và tên> – <MSSV>
- **Công cụ AI:** <Tên công cụ & Phiên bản/Model>
- **Mục tiêu:** <Mục tiêu cụ thể cần AI giải quyết>
- **Prompt chính:** <Câu lệnh/yêu cầu chính gửi cho AI>
- **Kết quả AI đưa ra:** <Tóm tắt phản hồi của AI>
- **Link / trích đoạn:** <Link chia sẻ cuộc hội thoại>
- **Phần đã kiểm tra:** <Cách thức kiểm tra tính đúng đắn>
- **Phần đã tự chỉnh sửa:** <Những điểm tự tay chỉnh sửa>
- **File bị ảnh hưởng:** <Danh sách file bị thay đổi>
```
