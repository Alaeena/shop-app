## DISCLAIMER: 
THIS PAGE WAS MADE AS A PERSONAL EDUCATIONAL PROJECT. This is NOT the official site of the company or brand identified on the page. The creator of this page is NOT affiliated with the company or brand in any way. This page is a personal project made in connection with an educational exercise.
## Website bán hàng thương mại điện tử Shop-app
# Table of Contents
* [Demo dự án](#Demo-dự-án)
* [Chức năng](#Chức-năng)
* [Công nghệ sử dụng](#Công-nghệ-sử-dụng)
* [Cài đặt và chạy thử](#Cài-đặt-và-chạy-thử)

##### shop-app là project đề án cá nhân, được xây dựng để hỗ trợ chức năng:
- bán hàng, quản lý sản phẩm
- mua bán, tìm kiếm sản phẩm với người dùng
- quản lý website với admin
###### Tài khoản admin:
- tk: admin
- mk: 123
###### Tài khoản người dùng:
- tk: ducnbk7a1@gmail.com
- mk: 123
 
## Chức năng
##### Người dùng:
- Tìm kiếm, mua bán sản phẩm
- Đăng nhập, đăng ký bằng jwt
- Cập nhật, chỉnh sửa thông tin cá nhân
- Chức năng tìm kiếm, phân trang, phân loại sản phẩm
- Thêm, sửa, xóa sản phẩm vào giỏ hàng
- Tạo, thanh toán, theo dõi và thao tác với hóa đơn
##### Admin:
- Thêm, sửa, xóa danh mục sản phẩm
- Thêm, vô hiệu hóa tài khoản người dùng
## Công nghệ sử dụng
- [ReactJS] - sử dụng để build giao diện, map dữ liệu
- [Spring boot] - Sử dụng để tạo api
- [PostgreSQL] - Hệ cơ sở dữ liệu

## Cài đặt và chạy thử

Để dễ dàng cài đặt project nhất, thì dự án sẽ sử dụng Docker. Để cho hướng dẫn đơn giản thì bài viết sẽ mặc định là bạn đã biết sử dụng và cài đặt docker

Mặc định, sau khi tải project về và giải nén ra ta sẽ được mô hình của project như sau:
 ![image](https://github.com/Alaeena/shop-app/assets/151113431/9a03e648-a49e-41d2-a4ac-9c91c581a41c)

Tiếp theo, chạy docker lên, sau đó chạy lệnh tại thư mục gốc:
```sh
docker-compose up
```
Mặc định, project sẽ sử dụng port 3000 để render giao diện. Project sẽ sử dụng những port sau:
( tất cả container sẽ chạy xong trong khoảng 1 phút )
- http://localhost:9200: server của elasticsearch
- http://localhost:8080: server của spring boot ( nơi chạy backend )
- http://localhost:3000: server của react ( nơi chạy giao diện )
