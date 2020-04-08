CREATE TABLE `login-user`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_code` varchar(36) COMMENT '用户编号',
    `username`  varchar(20) COMMENT '用户名',
    `password`  varchar(100) COMMENT '密码',
    `email`     varchar(100) COMMENT '邮箱',
    `tel`       varchar(11) COMMENT '手机号',
    `role`      int(10) COMMENT '角色',
    `avatar`    varchar(255) COMMENT '头像',
    `last_ip`   varchar(50) COMMENT '最后登录IP',
    `last_time` varchar(50) COMMENT '最后登录时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `login-user`
VALUES (1, 'd242ae49-4734-411e-8c8d-d2b09e87c3c8', 'root',
        '$2a$04$REdYt1gsbANkWtfhqjc9C.EqJM/k8qcQv2McNv/YGROZtOaFzzP4.', '1227814546@qq.com', '15588508711', 1, 'image',
        '127.0.0.1', '2019-10-21 11:26:27');