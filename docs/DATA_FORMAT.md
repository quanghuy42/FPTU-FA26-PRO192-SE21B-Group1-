# QUY ĐỊNH ĐỊNH DẠNG DỮ LIỆU & QUY TẮC ĐỌC/GHI FILE
**Dự án:** Dorm Utility Management System  
**Môn học:** PRO192 | **Lớp:** SE21B | **Nhóm:** Group 1  

Tài liệu này mô tả chi tiết cấu trúc lưu trữ dữ liệu dạng file văn bản (text file), định dạng từng trường, ý nghĩa nghiệp vụ và các quy tắc đọc/ghi dữ liệu đã được nhóm thống nhất.

---

## 1. Tổng quan lưu trữ
- Toàn bộ dữ liệu của chương trình được lưu dưới dạng file văn bản thuần (plain text) với mã hóa **UTF-8**.
- Vị trí lưu trữ: thư mục tương đối `data/` tính từ thư mục gốc của dự án (hoặc thư mục làm việc khi chạy ứng dụng `PRO192Project/data/`). Tuyệt đối không viết cứng (hardcode) đường dẫn tuyệt đối hay tên ổ đĩa (`C:\...`, `D:\...`).
- Quy cách phân tách:
  - Mỗi đối tượng được lưu trên **đúng 1 dòng**.
  - Các trường trên cùng một dòng được phân cách bởi dấu gạch đứng `|`.
  - Không có dòng tiêu đề (header line).
  - Không có các dòng trống thừa ở đầu, giữa hoặc cuối file.
  - Các giá trị tiền tệ và chỉ số tiêu thụ là các số nguyên không âm (đơn vị: VNĐ, kWh, m³).

---

## 2. Bảng tổng hợp các file dữ liệu

| Tên file | Vị trí | Mục đích lưu trữ | Số trường |
| :--- | :--- | :--- | :--- |
| `rooms.txt` | `data/rooms.txt` | Danh sách các phòng ký túc xá (STANDARD hoặc SERVICE) | 4 hoặc 5 |
| `students.txt` | `data/students.txt` | Danh sách sinh viên và thông tin xếp phòng ký túc xá | 3 |
| `prices.txt` | `data/prices.txt` | Bảng biểu giá hiện hành áp dụng cho tính toán mới | 5 |
| `bills.txt` | `data/bills.txt` | Danh sách hóa đơn ký túc xá (bản chụp giá tại thời điểm lập) | 13 |

---

## 3. Chi tiết định dạng từng file

### 3.1. `data/rooms.txt` (Danh sách phòng)
File lưu trữ thông tin các phòng ký túc xá. Hỗ trợ 2 loại phòng: phòng thường (`STANDARD`) và phòng dịch vụ (`SERVICE`).

- **Phòng thường (STANDARD):** gồm 4 trường
  ```text
  STANDARD|mãPhòng|sứcChứa|tiềnPhòng
  ```
- **Phòng dịch vụ (SERVICE):** gồm 5 trường (có thêm phí dịch vụ)
  ```text
  SERVICE|mãPhòng|sứcChứa|tiềnPhòng|phíDịchVụ
  ```

**Ý nghĩa các trường:**
1. `loạiPhòng`: Chuỗi ký tự, nhận giá trị `STANDARD` hoặc `SERVICE`.
2. `mãPhòng`: Chuỗi ký tự định danh duy nhất của phòng (ví dụ: `A101`, `A102`), không rỗng, không chứa khoảng trắng.
3. `sứcChứa`: Số nguyên trong khoảng từ `1` đến `10` (số người tối đa có thể ở).
4. `tiềnPhòng`: Số nguyên trong khoảng từ `0` đến `10.000.000` (tiền phòng cơ bản hàng tháng, đơn vị: VNĐ).
5. `phíDịchVụ` (chỉ có ở SERVICE): Số nguyên trong khoảng từ `0` đến `10.000.000` (đơn vị: VNĐ). Với phòng STANDARD thì phí dịch vụ ngầm định bằng 0.

**Dữ liệu mẫu:**
```text
STANDARD|A101|2|1200000
SERVICE|A102|3|1800000|300000
```

---

### 3.2. `data/students.txt` (Danh sách sinh viên & phân phòng)
File lưu trữ thông tin sinh viên kiêm trạng thái xếp phòng (không dùng file phân phòng riêng).

- **Định dạng 1 dòng:** gồm 3 trường
  ```text
  MSSV|họTên|mãPhòng
  ```

**Ý nghĩa các trường:**
1. `MSSV`: Mã số sinh viên, định danh duy nhất (ví dụ: `QE210001`), không rỗng, không chứa khoảng trắng.
2. `họTên`: Họ và tên sinh viên, không rỗng, không chứa ký tự phân cách `|`.
3. `mãPhòng`: Mã phòng sinh viên đang được xếp ở (phải tồn tại trong `rooms.txt`). Nếu sinh viên chưa được xếp vào phòng nào, ghi giá trị `NONE`.

**Dữ liệu mẫu:**
```text
QE210001|Nguyễn An|A101
QE210002|Trần Bình|A101
QE210003|Lê Chi|A102
QE210004|Phạm Dũng|NONE
```

---

### 3.3. `data/prices.txt` (Bảng giá hiện hành)
File lưu cấu hình giá hiện hành của hệ thống, chỉ gồm **đúng 1 dòng**.

- **Định dạng 1 dòng:** gồm 5 trường
  ```text
  tiềnPhòngThường|tiềnPhòngDịchVụ|phíDịchVụ|đơnGiáĐiện|đơnGiáNước
  ```

**Ý nghĩa các trường:**
1. `tiềnPhòngThường`: Giá phòng tiêu chuẩn hiện hành (VNĐ, từ `0` đến `10.000.000`).
2. `tiềnPhòngDịchVụ`: Giá phòng dịch vụ hiện hành (VNĐ, từ `0` đến `10.000.000`).
3. `phíDịchVụ`: Phí dịch vụ phòng hiện hành (VNĐ, từ `0` đến `10.000.000`).
4. `đơnGiáĐiện`: Đơn giá điện hiện hành cho mỗi kWh (VNĐ, từ `0` đến `10.000.000`).
5. `đơnGiáNước`: Đơn giá nước hiện hành cho mỗi m³ (VNĐ, từ `0` đến `10.000.000`).

**Dữ liệu mẫu:**
```text
1200000|1800000|300000|3000|15000
```

---

### 3.4. `data/bills.txt` (Danh sách hóa đơn)
Lưu lịch sử toàn bộ các hóa đơn đã lập. Ở bộ dữ liệu khởi đầu, file này là **file rỗng (0 bytes)**. Khi có hóa đơn, mỗi hóa đơn lưu trên một dòng gồm 13 trường.

- **Định dạng 1 dòng:** gồm 13 trường
  ```text
  mãHĐ|mãPhòng|YYYY-MM|điệnĐầu|điệnCuối|nướcĐầu|nướcCuối|loạiPhòng|tiềnPhòng|phíDịchVụ|đơnGiáĐiện|đơnGiáNước|trạngThái
  ```

**Ý nghĩa các trường:**
1. `mãHĐ`: Mã hóa đơn, định danh duy nhất (ví dụ: `HD001`, `HD002`), không rỗng, không chứa khoảng trắng.
2. `mãPhòng`: Mã phòng được lập hóa đơn (phải tồn tại trong `rooms.txt`).
3. `YYYY-MM`: Tháng tính tiền (chuỗi định dạng 4 chữ số năm, gạch nối, 2 chữ số tháng từ `01` đến `12`, ví dụ: `2026-10`).
4. `điệnĐầu`: Chỉ số điện đầu kỳ (số nguyên từ `0` đến `1.000.000`).
5. `điệnCuối`: Chỉ số điện cuối kỳ (số nguyên từ `0` đến `1.000.000`, bắt buộc `điệnCuối >= điệnĐầu`).
6. `nướcĐầu`: Chỉ số nước đầu kỳ (số nguyên từ `0` đến `1.000.000`).
7. `nướcCuối`: Chỉ số nước cuối kỳ (số nguyên từ `0` đến `1.000.000`, bắt buộc `nướcCuối >= nướcĐầu`).
8. `loạiPhòng`: `STANDARD` hoặc `SERVICE` (bản chụp loại phòng tại thời điểm lập hóa đơn).
9. `tiềnPhòng`: Tiền phòng tại thời điểm lập hóa đơn (bản chụp giá).
10. `phíDịchVụ`: Phí dịch vụ tại thời điểm lập hóa đơn (bản chụp giá, 0 với STANDARD).
11. `đơnGiáĐiện`: Đơn giá điện tại thời điểm lập hóa đơn (bản chụp giá).
12. `đơnGiáNước`: Đơn giá nước tại thời điểm lập hóa đơn (bản chụp giá).
13. `trạngThái`: Trạng thái thanh toán, nhận một trong hai giá trị: `UNPAID` (chưa thanh toán) hoặc `PAID` (đã thanh toán).

> **Lưu ý nghiệp vụ quan trọng:** Các trường 8 đến 12 (`loạiPhòng`, `tiềnPhòng`, `phíDịchVụ`, `đơnGiáĐiện`, `đơnGiáNước`) là **bản chụp giá (snapshot)** tại thời điểm lập hóa đơn. Nếu sau này biểu giá trong `prices.txt` thay đổi thì tiền của các hóa đơn cũ vẫn giữ nguyên, không bị ảnh hưởng.
>
> Công thức tính tổng tiền hóa đơn:
> $$\text{Tổng tiền} = \text{tiềnPhòng} + \text{phíDịchVụ} + (\text{điệnCuối} - \text{điệnĐầu}) \times \text{đơnGiáĐiện} + (\text{nướcCuối} - \text{nướcĐầu}) \times \text{đơnGiáNước}$$

---

## 4. Dữ liệu thử nghiệm (Fixture K10)
Để kiểm thử ca K10 (lưu dữ liệu -> thoát ứng dụng -> mở lại kiểm tra tính toàn vẹn và sinh mã tiếp theo), nhóm chuẩn bị bộ fixture đặt tại:
`PRO192Project/docs-test-data/k10/`

Bộ fixture này gồm:
- `rooms.txt`: Giống bộ mẫu.
- `students.txt`: Giống bộ mẫu.
- `prices.txt`: Giống bộ mẫu.
- `bills.txt`: Gồm đúng 2 dòng:
  ```text
  HD001|A101|2026-10|100|150|20|30|STANDARD|1200000|0|3000|15000|PAID
  HD002|A102|2026-10|100|150|20|30|SERVICE|1800000|300000|3000|15000|UNPAID
  ```

**Kỳ vọng kiểm thử K10:**
- Phòng A101 (HD001 - PAID): Tổng tiền = $1.200.000 + 0 + (50 \times 3.000) + (10 \times 15.000) = 1.500.000$ VNĐ (Đã thanh toán: 1.500.000 VNĐ).
- Phòng A102 (HD002 - UNPAID): Tổng tiền = $1.800.000 + 300.000 + (50 \times 3.000) + (10 \times 15.000) = 2.400.000$ VNĐ (Còn nợ: 2.400.000 VNĐ).
- Khi tạo hóa đơn mới tiếp theo, mã sinh tự động phải là `HD003` (không trùng với HD001 và HD002).

---

## 5. Quy tắc đọc và ghi file (Nhóm đã thống nhất)

### 5.1. Thời điểm lưu dữ liệu
- Ghi dữ liệu xuống file ngay sau **mỗi thao tác thành công**:
  - Xếp phòng cho sinh viên hoặc trả phòng.
  - Lập hóa đơn mới.
  - Xác nhận thanh toán hóa đơn.
  - Cập nhật thay đổi biểu giá.
- Ghi xác nhận toàn bộ một lần nữa khi người dùng chọn chức năng **Thoát chương trình**.

### 5.2. Quy trình ghi file an toàn (Atomic File Replacement)
- Để tránh nguy cơ mất hoặc hỏng dữ liệu khi mất điện đột ngột hoặc xảy ra lỗi trong quá trình ghi:
  1. Ghi toàn bộ nội dung mới ra một file tạm (ví dụ: `rooms.txt.tmp`).
  2. Đảm bảo luồng ghi hoàn tất thành công (`flush`, `close`).
  3. Tiến hành đổi tên/ghi đè file tạm lên file chính thức (`rooms.txt`).
- Nếu có bất kỳ ngoại lệ (I/O Exception) nào xảy ra:
  - Giữ nguyên file dữ liệu cũ, không làm hỏng dữ liệu hiện tại.
  - Báo lỗi rõ ràng cho người dùng trên màn hình console: `"Chưa lưu được dữ liệu, vui lòng thử lại!"`.

### 5.3. Xử lý khi nạp file hỏng hoặc sai định dạng
- Khi đọc file lúc khởi động hoặc lúc nạp:
  - Nếu phát hiện số lượng trường không khớp, dữ liệu không thể ép kiểu sang số, mã phòng không tồn tại, chỉ số âm, v.v.:
    - Báo lỗi rõ ràng trên console kèm **tên file** và **số thứ tự dòng** bị lỗi (ví dụ: `Lỗi đọc file data/rooms.txt tại dòng 2: sức chứa không hợp lệ`).
    - **TUYỆT ĐỐI KHÔNG** âm thầm khởi tạo danh sách rỗng để chạy tiếp.
    - **TUYỆT ĐỐI KHÔNG** tự ý ghi đè làm mất file dữ liệu cũ của người dùng.

### 5.4. Lần chạy đầu tiên (chưa có file dữ liệu)
- Nếu ứng dụng khởi động và phát hiện thư mục `data/` chưa có file:
  - Cho phép người dùng chọn: bắt đầu với cơ sở dữ liệu rỗng HOẶC nạp bộ dữ liệu mẫu ban đầu.
  - Khi thư mục `data/` đã tồn tại dữ liệu, ứng dụng luôn đọc từ dữ liệu hiện có, **tuyệt đối không tự ý nạp đè dữ liệu mẫu**.

### 5.5. Quy tắc sinh mã hóa đơn tự động
- Mã hóa đơn mới có định dạng tiền tố `HD` kèm số thứ tự (ví dụ: `HD001`, `HD002`, `HD003`...).
- Để đảm bảo không bao giờ trùng lặp kể cả sau khi thoát và mở lại chương trình:
  $$\text{Số thứ tự mới} = \max(\{\text{số thứ tự của tất cả hóa đơn trong bills.txt}\}) + 1$$
- Ví dụ: Trong `bills.txt` có `HD001` và `HD002`, mã hóa đơn tiếp theo chắc chắn là `HD003`.

### 5.6. Thứ tự đọc và nối quan hệ (Relationship Linking)
Khi khởi động hệ thống, việc nạp dữ liệu vào bộ nhớ phải tuân thủ đúng trình tự liên kết khóa ngoại:
1. **Bước 1 - Đọc `rooms.txt`:** Nạp danh sách các đối tượng `Room` (StandardRoom hoặc ServiceRoom) vào bộ nhớ.
2. **Bước 2 - Đọc `students.txt`:** 
   - Đọc từng sinh viên.
   - Nếu `mãPhòng` khác `NONE`: kiểm tra mã phòng đó có tồn tại trong danh sách phòng đã đọc ở Bước 1 hay không. Nếu có, thêm mã sinh viên vào danh sách người ở của phòng tương ứng (`room.addResident(studentId)`).
   - Kiểm tra số lượng sinh viên được gán vào mỗi phòng không được vượt quá `sứcChứa` của phòng đó.
3. **Bước 3 - Đọc `bills.txt`:** Nạp các hóa đơn, kiểm tra `mãPhòng` trên mỗi hóa đơn phải tham chiếu tới phòng hợp lệ.
4. **Bước 4 - Đọc `prices.txt`:** Nạp thông tin biểu giá hiện hành.

### 5.7. Ràng buộc và giới hạn hợp lệ của dữ liệu đầu vào (Validation Rules)
- **Định danh:**
  - `mãPhòng`, `MSSV`, `mãHĐ`: Không rỗng, không chứa khoảng trắng, là duy nhất trong toàn hệ thống.
  - `họTên`: Không rỗng, không chứa ký tự phân cách `|`.
- **Giới hạn số học:**
  - `sứcChứa`: Số nguyên từ `1` đến `10`.
  - `chỉ số điện/nước`: Số nguyên từ `0` đến `1.000.000`.
  - `chỉ số cuối >= chỉ số đầu`.
  - `tiềnPhòng`, `phíDịchVụ`, `đơnGiáĐiện`, `đơnGiáNước`: Số nguyên từ `0` đến `10.000.000` VNĐ.
  - `YYYY-MM`: Tháng phải có định dạng hợp lệ với năm gồm 4 chữ số và tháng từ `01` đến `12`.
