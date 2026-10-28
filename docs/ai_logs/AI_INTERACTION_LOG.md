# AI Interaction Log -- Lê Thành Luân

**Dự án:** Dorm Utility Management System\
**Môn học:** PRO192\
**Lớp / Nhóm:** SE21B / Group 1\
**Thành viên:** Lê Thành Luân -- QE200043\
**Phạm vi:** Các tương tác AI liên quan trực tiếp đến mã nguồn, thiết kế
kỹ thuật và kiểm thử.


------------------------------------------------------------------------

## Mục #7


-   **Ngày:** 2026-10-25
-   **Người thực hiện:** Lê Thành Luân -- QE200043
-   **Công cụ AI:** ChatGPT (GPT-6)
-   **Mục tiêu:** Xây dựng phần quản lý hồ sơ sinh viên cho hệ thống
    quản lý ký túc xá, bao gồm thông tin sinh viên, mã sinh viên và
    trạng thái đã được xếp phòng hay chưa.
-   **Prompt chính:** Yêu cầu AI hỗ trợ thiết kế và viết lớp
    `Student.java` theo yêu cầu của bài PRO192, sử dụng Java và đóng gói
    dữ liệu bằng các thuộc tính cùng phương thức phù hợp.
-   **Kết quả AI đưa ra:** Đề xuất lớp `Student` lưu mã sinh viên, họ
    tên và mã phòng hiện tại; cung cấp các phương thức truy vấn trạng
    thái xếp phòng, gán phòng và trả phòng. Logic cần ngăn dữ liệu đầu
    vào không hợp lệ và giữ trạng thái sinh viên nhất quán.
-   **Link / trích đoạn:** Trích đoạn tương tác về phần lớp
    `Student.java` trong lịch sử ChatGPT của Luân.
-   **Phần đã kiểm tra:** Đối chiếu tên thuộc tính và phương thức với
    yêu cầu của phần quản lý sinh viên; cần kiểm tra lại bằng cách biên
    dịch trong project tích hợp và thử các trường hợp mã/tên không hợp
    lệ.
-   **Phần đã tự chỉnh sửa:** Rà soát tên package, chữ ký phương thức và
    cách gọi từ lớp quản lý để phù hợp với cấu trúc project nhóm.
-   **File bị ảnh hưởng:** `Student.java`.

## Mục #8

-   **Ngày:** 2026-10-25
-   **Người thực hiện:** Lê Thành Luân -- QE200043
-   **Công cụ AI:** ChatGPT (GPT-6)
-   **Mục tiêu:** Xây dựng lớp quản lý sinh viên và các nghiệp vụ thêm,
    tìm kiếm, xếp phòng và trả phòng.
-   **Prompt chính:** Yêu cầu AI hỗ trợ triển khai
    `StudentManager.java`, quản lý danh sách sinh viên và kết nối thông
    tin sinh viên với các phòng hiện có.
-   **Kết quả AI đưa ra:** Đề xuất dùng danh sách sinh viên và bản đồ
    phòng để tra cứu theo mã; kiểm tra mã sinh viên trùng trước khi
    thêm; kiểm tra sinh viên và phòng tồn tại trước khi xếp phòng; cập
    nhật trạng thái sinh viên và thông tin người ở trong phòng khi xếp
    hoặc trả phòng.
-   **Link / trích đoạn:** Trích đoạn tương tác về phần lớp
    `StudentManager.java` trong lịch sử ChatGPT của Luân.
-   **Phần đã kiểm tra:** Đối chiếu luồng xử lý với các trường hợp thêm
    sinh viên, tìm kiếm theo mã, xếp phòng và trả phòng. Cần biên dịch
    cùng `Room.java` và xác nhận tên API thực tế của lớp `Room` trong
    branch tích hợp; chưa ghi nhận BUILD SUCCESSFUL nếu chưa chạy được.
-   **Phần đã tự chỉnh sửa:** Điều chỉnh khai báo package và rà soát lời
    gọi phương thức quản lý người ở trong phòng để khớp với API thực tế
    của lớp `Room`.
-   **File bị ảnh hưởng:** `StudentManager.java`, `Student.java` (phần
    tương tác giữa hai lớp).

## Mục #9

-   **Ngày:** 2026-10-26
-   **Người thực hiện:** Lê Thành Luân -- QE200043
-   **Công cụ AI:** ChatGPT (GPT-6)
-   **Mục tiêu:** Rà soát logic kiểm tra sức chứa và trạng thái xếp
    phòng theo các ca kiểm thử K04/K05.
-   **Prompt chính:** Yêu cầu AI xem xét các tình huống sinh viên đã có
    phòng, phòng không còn chỗ, mã phòng không hợp lệ, trả phòng và xếp
    lại phòng.
-   **Kết quả AI đưa ra:** Đề xuất kiểm tra trạng thái sinh viên trước
    khi xếp phòng; từ chối xếp khi sinh viên đã được gán phòng hoặc
    phòng đã đầy; khi trả phòng cần cập nhật cả danh sách người ở của
    phòng và trạng thái phòng của sinh viên; sau khi trả phòng, sinh
    viên có thể được xếp lại nếu các điều kiện hợp lệ.
-   **Link / trích đoạn:** Trích đoạn tương tác về logic K04/K05 trong
    lịch sử ChatGPT của Luân.
-   **Phần đã kiểm tra:** Dùng các tình huống K04/K05 làm danh sách kiểm
    tra logic. Chỉ đánh dấu PASS sau khi chạy test thực tế trong project
    nhóm và lưu kết quả đầu ra; các kết quả chưa chạy cần được để ở
    trạng thái chưa xác nhận.
-   **Phần đã tự chỉnh sửa:** Đối chiếu điều kiện kiểm tra và thứ tự cập
    nhật trạng thái để tránh sinh viên bị gán hai phòng hoặc dữ liệu
    phòng/sinh viên không đồng nhất.
-   **File bị ảnh hưởng:** `Student.java`, `StudentManager.java` và phần
    test driver liên quan nếu có.

## Mục #10

-   **Ngày:** 2026-10-28
-   **Người thực hiện:** Lê Thành Luân -- QE200043
-   **Công cụ AI:** ChatGPT (GPT-6)
-   **Mục tiêu:** Rà soát việc tích hợp hai lớp sinh viên vào cấu trúc
    package của project nhóm và xác định các vấn đề biên dịch khi ghép
    với lớp `Room`.
-   **Prompt chính:** Cung cấp ảnh NetBeans và yêu cầu hướng dẫn tích
    hợp `Student.java`, `StudentManager.java` vào
    `PRO192Project/src/pro192project/`, đồng thời đổi package sang
    `pro192project`.
-   **Kết quả AI đưa ra:** Xác định hai file ban đầu nằm trong package
    `com.mycompany.dev.luan`; hướng dẫn đổi khai báo package thành
    `package pro192project;` và nhấn mạnh cần đối chiếu các phương thức
    được gọi trong `StudentManager` với API thật của `Room` trong
    project nhóm. Ảnh NetBeans tại thời điểm trao đổi cho thấy kiểu
    `Room` đang bị gạch đỏ trong `StudentManager.java`, nên cần xử lý
    phụ thuộc/package/API trước khi kết luận biên dịch thành công.
-   **Link / trích đoạn:** Trích đoạn tương tác về tích hợp package và
    kiểm tra lỗi `Room` trong lịch sử ChatGPT của Luân.
-   **Phần đã kiểm tra:** Quan sát lỗi trong trình soạn thảo NetBeans;
    chưa xác nhận build thành công. Cần kiểm tra lại sau khi đưa file
    vào đúng project và sửa các tham chiếu không tương thích.
-   **Phần đã tự chỉnh sửa:** Thực hiện hoặc sẽ thực hiện việc đổi
    package và điều chỉnh lời gọi phương thức theo mã nguồn thực tế của
    nhóm; cập nhật mục này sau khi hoàn tất.
-   **File bị ảnh hưởng:** `Student.java`, `StudentManager.java`.

------------------------------------------------------------------------


