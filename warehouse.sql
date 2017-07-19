
    drop table if exists warehouse.chucvu;

    drop table if exists warehouse.congtrinh;

    drop table if exists warehouse.donvi;

    drop table if exists warehouse.dutoan;

    drop table if exists warehouse.hanghoa;

    drop table if exists warehouse.history;

    drop table if exists warehouse.loaihanghoa;

    drop table if exists warehouse.nguoiban;

    drop table if exists warehouse.taikhoan;

    drop table if exists warehouse.vatlieunhap;

    drop table if exists warehouse.vatlieutonkho;

    drop table if exists warehouse.vatlieuxuat;

    create table warehouse.chucvu (
        machucvu varchar(255) not null,
        tenchucvu varchar(255) not null,
        primary key (machucvu)
    );

    create table warehouse.congtrinh (
        macongtrinh varchar(255) not null,
        diachicongtrinh varchar(255) not null,
        ngaybatdau varchar(255) not null,
        ngaycapnhat varchar(255) not null,
        ngayketthuc varchar(255) not null,
        tencongtrinh varchar(255) not null,
        trangthai varchar(255) not null,
        primary key (macongtrinh)
    );

    create table warehouse.donvi (
        madonvi varchar(255) not null,
        tendonvi varchar(255) not null,
        primary key (madonvi)
    );

    create table warehouse.dutoan (
        mahanghoa varchar(255) not null,
        macongtrinh varchar(255) not null,
        khoiluongdutoan integer not null,
        tongkhoiluong integer not null,
        primary key (mahanghoa, macongtrinh)
    );

    create table warehouse.hanghoa (
        mahanghoa varchar(255) not null,
        gia integer not null,
        donvi varchar(255) not null,
        maloaihanghoa varchar(255) not null,
        tenhanghoa varchar(255) not null,
        primary key (mahanghoa)
    );

    create table warehouse.history (
        id bigint not null auto_increment,
        action varchar(255) not null,
        datemodify varchar(255) not null,
        datenew varchar(255) not null,
        dateold varchar(255) not null,
        khoiluongcu integer not null,
        khoiluongmoi integer not null,
        macongtrinh varchar(255) not null,
        mahanghoacu varchar(255) not null,
        mahanghoamoi varchar(255) not null,
        tennguoidung varchar(255) not null,
        type varchar(255) not null,
        primary key (id)
    );

    create table warehouse.loaihanghoa (
        maloaihanghoa varchar(255) not null,
        tenloaihanghoa varchar(255) not null,
        primary key (maloaihanghoa)
    );

    create table warehouse.nguoiban (
        manguoiban varchar(255) not null,
        diachi varchar(255) not null,
        email varchar(255) not null,
        sodienthoai varchar(255) not null,
        tennguoiban varchar(255) not null,
        primary key (manguoiban)
    );

    create table warehouse.taikhoan (
        id bigint not null auto_increment,
        deleted bit not null,
        diachi varchar(255) not null,
        email varchar(255) not null,
        machucvu varchar(255) not null,
        password varchar(255) not null,
        role integer not null,
        sodienthoai varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    );

    create table warehouse.vatlieunhap (
        id bigint not null auto_increment,
        khoiluong integer not null,
        macongtrinh varchar(255) not null,
        mahanghoa varchar(255) not null,
        manguoinhap varchar(255) not null,
        ngaynhap varchar(255) not null,
        primary key (id)
    );

    create table warehouse.vatlieutonkho (
        mahanghoa varchar(255) not null,
        macongtrinh varchar(255) not null,
        khoiluongton integer not null,
        primary key (mahanghoa, macongtrinh)
    );

    create table warehouse.vatlieuxuat (
        id bigint not null auto_increment,
        khoiluong integer not null,
        macongtrinh varchar(255) not null,
        mahanghoa varchar(255) not null,
        manguoixuat varchar(255) not null,
        ngayxuat varchar(255) not null,
        primary key (id)
    );
	INSERT INTO warehouse.taikhoan (`deleted`, `diachi`, `email`, `machucvu`, `password`, `role`, `sodienthoai`, `username`) VALUES(0, 'Root', 'root@root', 'root', 'e10adc3949ba59abbe56e057f20f883e', '1', '000000000', 'root');