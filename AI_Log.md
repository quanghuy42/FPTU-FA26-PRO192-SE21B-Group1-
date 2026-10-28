# NHẬT KÝ TƯƠNG TÁC VÀ SỬ DỤNG TRỢ LÝ AI (AI INTERACTION LOG)

- **Tên dự án:** Dorm Utility Management System (Hệ thống Quản lý Ký túc xá & Tiền điện nước)
- **Môn học:** PRO192 | **Lớp:** SE21B | **Nhóm:** Group 1
- **Vị trí tài liệu:** `docs/ai_logs/AI_log_danghuy.md`

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
| **#1** | 2026-10-26 | Nguyễn Đăng Huy - QE210182 | Gemini (3 Flash) | Định hướng thiết kế & quy trình kiểm tra dữ liệu cho phần việc Thành viên 3 (`BillManager`, `PriceSnapshot`) | `BillManager.java`, `PriceSnapshot.java`, `Bill.java` |
| **#2** | 2026-10-26 | Nguyễn Đăng Huy - QE210182 | Gemini (3 Flash) | Khắc phục lỗi `cannot find symbol` và `Unclosed group` do nhãn tham số NetBeans chèn vào mã | `BillManager.java`, `Main.java` |
| **#3** | 2026-10-26 | Nguyễn Đăng Huy - QE210182 | Gemini (3 Flash) | Xây dựng test driver nhập liệu bằng `Scanner` và sửa lỗi vẫn in `[THANH CONG]` sau khi bắt ngoại lệ | `Main.java` (Test Driver) |

---

## 3. Chi tiết từng lần tương tác

### Mục #1
- **Ngày:** 2026-10-26
- **Người thực hiện:** Nguyễn Đăng Huy - QE210182
- **Công cụ AI:** Gemini (model Gemini 3 Flash, qua giao diện Gemini trên web)
- **Mục tiêu:** Hiểu rõ nghiệp vụ lập hóa đơn của Thành viên 3 ("nhập chỉ số/tháng, lập hóa đơn và bản chụp giá") và thiết kế cấu trúc lớp `BillManager`, `PriceSnapshot` đáp ứng các yêu cầu kiểm tra dữ liệu đầu vào.
- **Prompt chính:** "Hướng dẫn tôi thiết kế lớp BillManager và PriceSnapshot trong Java cho bài toán lập hóa đơn ký túc xá, cần kiểm tra phòng có người, định dạng tháng YYYY-MM, chỉ số điện nước cuối >= đầu."
- **Kết quả AI đưa ra:** 
  - Định hướng thiết kế lớp `PriceSnapshot` để lưu lại đơn giá điện/nước tại thời điểm chốt hóa đơn.
  - Xây dựng phương thức `createBill(...)` trong `BillManager`, thực hiện lần lượt các bước kiểm tra hợp lệ (validate):
    - Phòng phải có người ở (`currentOccupancy > 0`).
    - Tháng phải đúng định dạng `YYYY-MM` (Regex `^\\d{4}-(0[1-9]|1[0-2])$`).
    - Chỉ số điện/nước cuối phải lớn hơn hoặc bằng chỉ số đầu.
    - Tính duy nhất: không cho phép tạo 2 hóa đơn trùng cặp `roomId` và `billingMonth`.
- **Link / trích đoạn:** (https://gemini.google.com/app/c73a338a21afdfcb)
- **Phần đã kiểm tra:** Đối chiếu các điều kiện ràng buộc với yêu cầu của các ca kiểm thử K02, K03, K06, K07 trong tài liệu đặc tả đề bài môn học PRO192. (điền: ca nào đã thực sự chạy và kết quả)
- **Phần đã tự chỉnh sửa:** Điều chỉnh các thông điệp ngoại lệ (Exception message) để thống nhất cách hiển thị tiếng Việt (có dấu/không dấu) với toàn bộ dự án.
- **File bị ảnh hưởng:** `BillManager.java`, `PriceSnapshot.java`, `Bill.java`.

---

### Mục #2
- **Ngày:** 2026-10-26
- **Người thực hiện:** Nguyễn Đăng Huy - QE210182
- **Công cụ AI:** Gemini (model Gemini 3 Flash, qua giao diện Gemini trên web)
- **Mục tiêu:** Xử lý các lỗi báo đỏ xuất hiện trên NetBeans 17 khi dùng `Scanner` nhập dữ liệu: `cannot find symbol` và lỗi biểu thức chính quy `Unclosed group`.
- **Prompt chính:** "Sửa lỗi biên dịch NetBeans cannot find symbol và lỗi Unclosed group trong Java khi dùng Scanner nhập dữ liệu."
- **Kết quả AI đưa ra:** 
  - *Nguyên nhân:* NetBeans tự động hiển thị nhãn tên tham số (ví dụ: `regex:`, `formatter:`) ngay trong trình soạn thảo, dẫn đến việc sao chép nhầm các nhãn này vào mã nguồn gốc.
  - *Cách sửa:* chuẩn hóa lại biểu thức chính quy kiểm tra tháng; loại bỏ các ký tự thừa do IDE hiển thị.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Thực hiện Clean and Build trên NetBeans 17 thành công (BUILD SUCCESSFUL, 0 lỗi biên dịch).
- **Phần đã tự chỉnh sửa:** Xóa hoàn toàn các chuỗi nhãn tham số thừa trong mã nguồn.
- **File bị ảnh hưởng:** `BillManager.java`, `Main.java`.

---

### Mục #3
- **Ngày:** 2026-10-26
- **Người thực hiện:** Nguyễn Đăng Huy - QE210182
- **Công cụ AI:** Gemini (model Gemini 3 Flash, qua giao diện Gemini trên web)
- **Mục tiêu:** Xây dựng hàm `main` nhập liệu tương tác qua `Scanner` để kiểm thử thực tế, đồng thời sửa lỗi chương trình vẫn in dòng `=> [THANH CONG]` dù đã bắt được ngoại lệ.
- **Prompt chính:** "Code main của tôi bị lỗi khi nhập sai nó nhảy vào catch nhưng sau đó vẫn in ra [THANH CONG] ở cuối, hãy sửa giúp tôi."
- **Kết quả AI đưa ra:** 
  - *Nguyên nhân:* câu lệnh in thông báo thành công được đặt ngoài khối `try`, nên vẫn được thực thi tiếp sau khối `catch`.
  - *Cách sửa:* tái cấu trúc khối `try-catch-finally` trong hàm `main`, đặt lời gọi `createBill` và câu lệnh in thông báo thành công liền kề nhau trong cùng một khối `try`. Khi có ngoại lệ, luồng thực thi nhảy ngay sang `catch` và không còn in nhầm trạng thái thành công.
- **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
- **Phần đã kiểm tra:** Chạy `Run File` trên NetBeans 17 với các dữ liệu sai: tháng `2026-12-05`, chỉ số nước cuối < đầu, phòng trống. Kết quả: console báo `[LOI NGHIEP VU BAT DUOC]` và không còn xuất hiện dòng `=> [THANH CONG]`.
- **Phần đã tự chỉnh sửa:** Chuẩn hóa định dạng hiển thị thông báo trên console để dễ quan sát khi demo.
- **File bị ảnh hưởng:** `Main.java` (Test Driver).

---

## 4. Mẫu nhật ký (Template dành cho các thành viên bổ sung)

Khi bất kỳ thành viên nào trong nhóm sử dụng AI để hỗ trợ công việc liên quan đến mã nguồn hoặc kiểm thử, hãy copy biểu mẫu dưới đây, điền đầy đủ thông tin và dán tiếp vào cuối tài liệu này:

```markdown
### Mục #<Số thứ tự>
- **Ngày:** YYYY-MM-DD
- **Người thực hiện:** <Họ tên và> – <MSSV>
- **Công cụ AI:** <Tên & 3.5 Antigravity ChatGPT Claude GPT-4o, Phiên Sonnet, bản/Model, công cụ dụ: ví>
- **Mục tiêu:** <Mục AI cần cụ giải quyết thể tiêu>
- **Prompt chính:** <Câu AI cho chính cầu gửi lệnh/yêu>
- **Kết quả AI đưa ra:** <Tóm AI gì hồi/sinh mã/gợi những phản tắt ý đã>
- **Link / trích đoạn:** <Link chia cuộc hoặc hội prompt quan sẻ thoại trích trọng đoạn>
- **Phần đã kiểm tra:** <Cách AI bạn code của do kiểm sinh thức tra tính đúng đắn>
- **Phần đã tự chỉnh sửa:** <Những AI bạn của gốc hồi lại phản so sửa tay tự với điểm>
- **File bị ảnh hưởng:** <Danh bị các file hoặc mới project sách thay trong tạo đổi>
