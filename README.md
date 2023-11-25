## DISCLAIMER: 
THIS PAGE WAS MADE AS A PERSONAL EDUCATIONAL PROJECT. This is NOT the official site of the company or brand identified on the page. The creator of this page is NOT affiliated with the company or brand in any way. This page is a personal project made in connection with an educational exercise.
## Website bán hàng thương mại điện tử Shop-app
# Table of Contents
* [Demo dự án](#demo)
* [Chức năng](#function)
* [Công nghệ sử dụng](#technology)
* [Cài đặt và chạy thử](#installation)

##### shop-app là project đề án cá nhân, được xây dựng để hỗ trợ chức năng:
- bán hàng, quản lý sản phẩm
- mua bán, tìm kiếm sản phẩm với người dùng
- quản lý website với admin
## Demo dự án:
link demo project: http://shop-app-front.s3-website-ap-southeast-1.amazonaws.com/
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
- [Elasticsearch] - Hệ quản lý dữ liệu phụ trợ để thực hiện tìm kiếm sản phẩm

## Cài đặt và chạy thử

Để dễ dàng cài đặt project nhất, thì có thể sử dụng Docker. Để cho hướng dẫn đơn giản thì bài viết sẽ mặc định là bạn đã biết sử dụng và cài đặt docker

Mặc định, sau khi tải project về và giải nén ra ta sẽ được mô hình của project như sau:
 ![image](https://github.com/Alaeena/shop-app/assets/151113431/9a03e648-a49e-41d2-a4ac-9c91c581a41c)

Chạy docker lên, sau đó chạy lệnh:
```sh
docker-compose up
```
Mặc định, project sẽ sử dụng port 3000. Ta có những port sau:
( tất cả contianer sẽ chạy xong trong khoảng 1 phút )
- http://localhost:9200: server của elasticsearch
- http://localhost:8080: server của spring boot ( nơi chạy backend )
- http://localhost:3000: server của react ( nơi chạy giao diện )
