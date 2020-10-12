CREATE TABLE `chinese_poetry_shi` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `dynasty` varchar(10) DEFAULT NULL COMMENT '朝代',
  `paragraphs` text COMMENT '段落',
  `title` varchar(300) DEFAULT NULL COMMENT '标题',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=305952 DEFAULT CHARSET=utf8mb4;