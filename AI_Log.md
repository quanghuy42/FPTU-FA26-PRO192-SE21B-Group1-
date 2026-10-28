# NHẬT KÝ TƯƠNG TÁC VÀ SỬ DỤNG TRỢ LÝ AI (AI INTERACTION LOG)

* **Tên dự án:** Dorm Utility Management System (Hệ thống Quản lý Ký túc xá \& Tiền điện nước)
* **Môn học:** PRO192 | **Lớp:** SE21B | **Nhóm:** Group 1

\---

## 1\. Quy ước ghi nhật ký sử dụng AI

Theo yêu cầu đề bài môn học PRO192, việc sử dụng các công cụ hỗ trợ bằng Trí tuệ Nhân tạo (GenAI) phải tuân thủ nghiêm ngặt các nguyên tắc sau:

1. **Trách nhiệm cá nhân:** Mỗi thành viên tự ghi chép các lần mình sử dụng AI và tự commit/push lên repository từ tài khoản GitHub cá nhân tương ứng.
2. **Tính trung thực:** Ghi nhận trung thực, đầy đủ mọi lần tương tác quan trọng có ảnh hưởng đến mã nguồn, bản thiết kế kỹ thuật hoặc kịch bản kiểm thử (test driver/test cases).
3. **Phạm vi ghi nhận:** **CHỈ** ghi các tương tác liên quan trực tiếp đến **CODE, THIẾT KẾ KỸ THUẬT, VÀ KIỂM THỬ**. Không ghi các nội dung phụ trợ như viết README chung chung hay các hướng dẫn thao tác cơ bản về Git/GitHub/SourceTree.
4. **Nắm vững mã nguồn:** Công cụ AI chỉ đóng vai trò trợ lý/cố vấn. Mỗi thành viên phải tự kiểm tra, thẩm định, hiểu tường tận toàn bộ mã nguồn mình nộp và chịu trách nhiệm giải trình trước giảng viên khi được vấn đáp.
5. **Bảo mật thông tin:** Tuyệt đối không lưu trữ email cá nhân, token, mật khẩu hay dữ liệu nhạy cảm vào nhật ký.

\---

## 2\. Bảng tổng hợp các lần tương tác AI

|#|Ngày|Người thực hiện|Công cụ AI|Mục tiêu chính|File / Phần ảnh hưởng|
|:-:|:-:|-|-|-|-|
|**#1**|2026-10-26|Lê Trần Khải Nguyên - QE210233|Claude (Sonnet 5.5)|Hiểu nhiệm vụ lưu/đọc file, chọn thứ tự làm và thiết kế cách ghi một đối tượng thành một dòng text|`DemoStorage.java` (bản thử ban đầu)|
|**#2**|2026-10-26|Lê Trần Khải Nguyên - QE210233|Claude (Sonnet 5.5)|Viết lại phần lưu/đọc `students.txt` theo quy ước không dùng `static` (trừ `main`), tách lớp riêng|`Student.java`, `StudentStorage.java`, `DemoStorage.java`|
|**#3**|2026-10-27|Lê Trần Khải Nguyên - QE210233|Claude (Sonnet 5.5)|Mở rộng lưu/đọc cho 4 file dữ liệu, gộp entity + storage thành một lớp, thêm kiểm tra dữ liệu khi đọc và test driver theo K10|`Student.java`, `Room.java`, `Price.java`, `Bill.java`, `DemoStorage.java`|
|**#4**|2026-10-27|Lê Trần Khải Nguyên - QE210233|Claude (Sonnet 5.5)|Đổi tên lớp tránh trùng với lớp của thành viên khác và chuẩn hóa khai báo `package` theo cấu trúc dự án|`StorageStudent.java`, `StorageRoom.java`, `StoragePrice.java`, `StorageBill.java`, `DemoStorage.java`|

\---

## 3\. Chi tiết từng lần tương tác

### Mục #1

* **Ngày:** 2026-10-26
* **Người thực hiện:** Lê Trần Khải Nguyên - QE210233
* **Công cụ AI:** Claude (model Claude Sonnet 5.5, qua giao diện Claude trên web)
* **Mục tiêu:** Hiểu "lưu/đọc file" trong phần việc Storage của nhóm và xác định nên làm từ đâu; thiết kế cách biến một đối tượng thành một dòng văn bản để ghi vào file `.txt` rồi đọc lại.
* **Prompt chính:** "Tôi sẽ làm từ phần 1, tôi đang không hiểu lưu/đọc file là làm gì và nên bắt đầu từ đâu."
* **Kết quả AI đưa ra:**

  * Giải thích: *lưu* là biến từng đối tượng trong `List` thành một dòng text (các trường cách nhau bằng dấu `|`); *đọc* là tách từng dòng rồi tạo lại đối tượng.
  * Đề xuất bắt đầu từ `students.txt` (ít trường nhất), sau đó mới làm `prices.txt`, `rooms.txt`, `bills.txt`.
  * Code mẫu lưu/đọc `students.txt`:

    * Ghi ra file tạm `.tmp` rồi mới `Files.move(..., REPLACE\_EXISTING)` để lỗi giữa chừng không làm hỏng file cũ.
    * Tách dòng bằng `split("\\\\|", -1)`; báo lỗi nêu rõ file và số dòng khi sai số trường.
    * Quy ước `NONE` ↔ `null` cho sinh viên chưa xếp phòng; đọc/ghi bằng UTF-8.
* **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
* **Phần đã kiểm tra:** Đối chiếu với yêu cầu lưu/đọc trong tài liệu đặc tả (ghi file an toàn, báo lỗi kèm file và dòng khi file hỏng, chưa có file thì bắt đầu với dữ liệu rỗng). (điền: đã chạy thử lưu → mở `students.txt` → đọc lại, và thử sửa file thiếu trường để xem thông báo lỗi; kết quả thực tế)
* **Phần đã tự chỉnh sửa:** (điền: nếu có. Ở lần này chưa chỉnh gì; yêu cầu sửa theo quy ước nhóm nằm ở Mục #5)
* **File bị ảnh hưởng:** `DemoStorage.java` (bản thử ban đầu, có lớp `Student` tạm lồng bên trong).

\---

### Mục #2

* **Ngày:** 2026-10-26
* **Người thực hiện:** Lê Trần Khải Nguyên - QE210233
* **Công cụ AI:** Claude (model Claude Sonnet 5.5, qua giao diện Claude trên web)
* **Mục tiêu:** Viết lại phần lưu/đọc `students.txt` theo quy ước không dùng `static` ở các hàm (trừ `main`), đồng thời tách `Student` thành lớp riêng.
* **Prompt chính:** "Viết lại đi, thấy tôi không cho dùng static ở các hàm (trừ hàm main)."
* **Kết quả AI đưa ra:**

  * Tách thành 3 file: `Student`, `StudentStorage` và `DemoStorage` (chỉ có `main`).
  * Đường dẫn file được truyền một lần vào constructor của `StudentStorage` và lưu trong thuộc tính; `save(list)` và `load()` là phương thức thường (không `static`).
  * Giữ nguyên các quy tắc ghi file tạm, báo lỗi theo dòng và quy ước `NONE` ↔ `null`.
* **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
* **Phần đã kiểm tra:** Rà lại từng phương thức để chắc không còn `static` ngoài `main`. (điền: đã chạy `DemoStorage` trên NetBeans và kiểm tra nội dung `students.txt`; kết quả thực tế)
* **Phần đã tự chỉnh sửa:** Tự nêu quy ước "không dùng `static` ở các hàm (trừ `main`)" và yêu cầu AI viết lại theo quy ước đó. (điền thêm nếu có sửa tay khác)
* **File bị ảnh hưởng:** `Student.java`, `StudentStorage.java`, `DemoStorage.java`.

\---

### Mục #3

* **Ngày:** 2026-10-27
* **Người thực hiện:** Lê Trần Khải Nguyên - QE210233
* **Công cụ AI:** Claude (model Claude Sonnet 5.5, qua giao diện Claude trên web)
* **Mục tiêu:** Mở rộng phần lưu/đọc cho cả 4 file dữ liệu (`rooms.txt`, `students.txt`, `prices.txt`, `bills.txt`), gộp lớp thực thể và lớp lưu trữ thành một lớp, thêm kiểm tra dữ liệu khi đọc file và viết test driver theo tình huống K10.
* **Prompt chính:** "Gom 4 file rooms.txt, students.txt, prices.txt, bills.txt vào chung 1 nhóm, gom class student và studentstorage lại thành 1, tương tự với các phần còn lại, DemoStorage là hàm main chính của 4 cái đó."
* **Kết quả AI đưa ra:**

  * Gom thành 1 package với 4 lớp `Student`, `Room`, `Price`, `Bill`; mỗi lớp có `toLine()`, `save(...)`, `load(...)` là phương thức thường (không `static`) và một constructor rỗng dùng để gọi `save/load`.
  * Kiểm tra dữ liệu khi đọc file, báo lỗi kèm tên file và số dòng:

    * `Room`: loại `STANDARD`/`SERVICE` (4 hoặc 5 trường), sức chứa từ 1 đến 10, số tiền không âm, không trùng mã phòng.
    * `Price`: đúng một dòng 5 trường, các số không âm.
    * `Bill`: đủ 13 trường, tháng đúng `YYYY-MM`, chỉ số điện/nước tối đa 1.000.000 và chỉ số cuối ≥ đầu, trạng thái `UNPAID`/`PAID`, không trùng mã hóa đơn, không trùng cặp phòng + tháng.
  * `Bill` lưu bản chụp giá (Price Snapshot) tại thời điểm lập hóa đơn, có `getTotal()` tính theo giá đã chụp và `nextBillId(...)` sinh mã `HDxxx` bằng số lớn nhất hiện có cộng 1.
  * `DemoStorage` tạo dữ liệu mẫu theo K10 (A101 đã trả, A102 còn nợ), lưu cả 4 file, đọc lại và in kết quả.
* **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
* **Phần đã kiểm tra:** Đối chiếu kết quả mong đợi của K10 trong tài liệu đặc tả: tổng nợ 2.400.000, mã hóa đơn kế tiếp `HD003`, và K09 về việc giữ đúng giá đã chụp khi đổi giá. (điền: kết quả chạy `DemoStorage` thực tế và các thử nghiệm làm hỏng file, ví dụ đổi `PAID` thành giá trị khác, xóa bớt một trường)
* **Phần đã tự chỉnh sửa:** Chủ động chọn cách gộp thực thể và lưu trữ vào cùng một lớp cho mỗi file dữ liệu. (điền thêm nếu có sửa tay khác)
* **File bị ảnh hưởng:** `Student.java`, `Room.java`, `Price.java`, `Bill.java`, `DemoStorage.java`.

\---

### Mục #4

* **Ngày:** 2026-10-27
* **Người thực hiện:** Lê Trần Khải Nguyên - QE210233
* **Công cụ AI:** Claude (model Claude Sonnet 5.5, qua giao diện Claude trên web)
* **Mục tiêu:** Đổi tên 4 lớp để không trùng với `Student`, `Room`, `Price`, `Bill` của các thành viên khác; sau đó chuẩn hóa dòng khai báo `package` cho khớp cấu trúc thư mục dự án `PRO192Project/src/pro192project/`.
* **Prompt chính:** "Viết lại các đoạn code ở trên với tên class mới như là StorageStudent, StorageBill,..." và "Dời 4 file sang PRO192Project/src/pro192project/, đổi package thành `package pro192project;`, giờ sửa như thế nào?"
* **Kết quả AI đưa ra:**

  * Đổi tên thành `StorageStudent`, `StorageRoom`, `StoragePrice`, `StorageBill`; cập nhật constructor, kiểu `List<...>` và các lời gọi `new` trong `DemoStorage`.
  * Giải thích dòng `package` phải khớp thư mục chứa file (nếu lệch thì NetBeans báo lỗi biên dịch), nên cần đổi `package com.mycompany.dev.nguyen;` thành `package pro192project;` khi dời file sang thư mục mới.
  * Lưu ý khi sửa tay dòng `package` nên dùng trình soạn thảo thông thường, tránh ghi đè file bằng lệnh có thể thêm ký tự BOM làm `javac` báo lỗi.
* **Link / trích đoạn:** (điền link chia sẻ cuộc trò chuyện)
* **Phần đã kiểm tra:** Kiểm tra tên lớp, tên tệp và dòng `package` trong từng file đã khớp nhau. (điền: kết quả Clean and Build trên NetBeans và kết quả chạy lại `DemoStorage` sau khi đổi tên/dời file)
* **Phần đã tự chỉnh sửa:** Tự dời 4 file sang thư mục mới và tự sửa dòng `package` trong từng file. (điền thêm nếu có sửa tay khác)
* **File bị ảnh hưởng:** `StorageStudent.java`, `StorageRoom.java`, `StoragePrice.java`, `StorageBill.java`, `DemoStorage.java`.

\---

## 4\. Mẫu nhật ký (Template dành cho các thành viên bổ sung)

Khi bất kỳ thành viên nào trong nhóm sử dụng AI để hỗ trợ công việc liên quan đến mã nguồn hoặc kiểm thử, hãy copy biểu mẫu dưới đây, điền đầy đủ thông tin và dán tiếp vào cuối tài liệu này:

```markdown
### Mục #<Số thứ tự>
- \*\*Ngày:\*\* YYYY-MM-DD
- \*\*Người thực hiện:\*\* <Họ và tên> – <MSSV>
- \*\*Công cụ AI:\*\* <Tên công cụ \& phiên bản/model, ví dụ: ChatGPT GPT-4o, Claude Sonnet, Gemini 3.5, Antigravity>
- \*\*Mục tiêu:\*\* <Mục tiêu cụ thể cần AI giải quyết>
- \*\*Prompt chính:\*\* <Câu lệnh/yêu cầu chính gửi cho AI>
- \*\*Kết quả AI đưa ra:\*\* <Tóm tắt những gì AI phản hồi/sinh mã/gợi ý>
- \*\*Link / trích đoạn:\*\* <Link chia sẻ cuộc hội thoại hoặc trích đoạn prompt quan trọng>
- \*\*Phần đã kiểm tra:\*\* <Cách bạn kiểm tra tính đúng đắn của code do AI sinh>
- \*\*Phần đã tự chỉnh sửa:\*\* <Những điểm bạn đã tự sửa lại so với phản hồi gốc của AI>
- \*\*File bị ảnh hưởng:\*\* <Danh sách các file thay đổi hoặc mới tạo trong project>
```

