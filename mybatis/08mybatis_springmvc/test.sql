CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '',
  `mobile` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES ('1', 'xiaoming', '13111111111');
INSERT INTO `user` VALUES ('2', 'zhaosi', '15666666666');

CREATE TABLE `order` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0',
  `order_no` varchar(16) NOT NULL DEFAULT '',
  `money` float(10,2) unsigned DEFAULT '0.00',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
INSERT INTO `order` VALUES ('1', '1', '1509289090', '99.90');
INSERT INTO `order` VALUES ('2', '1', '1519289091', '290.80');
INSERT INTO `order` VALUES ('3', '1', '1509294321', '919.90');
INSERT INTO `order` VALUES ('4', '1', '1601232190', '329.90');
INSERT INTO `order` VALUES ('5', '1', '1503457384', '321.00');
INSERT INTO `order` VALUES ('6', '1', '1598572382', '342.00');
INSERT INTO `order` VALUES ('7', '1', '1500845727', '458.00');
INSERT INTO `order` VALUES ('8', '1', '1508458923', '1200.00');
INSERT INTO `order` VALUES ('9', '1', '1504538293', '2109.00');
INSERT INTO `order` VALUES ('10', '1', '1932428723', '5888.00');
INSERT INTO `order` VALUES ('11', '1', '2390423712', '3219.00');
INSERT INTO `order` VALUES ('12', '1', '4587923992', '123.00');
INSERT INTO `order` VALUES ('13', '1', '4095378812', '421.00');
INSERT INTO `order` VALUES ('14', '1', '9423890127', '678.00');
INSERT INTO `order` VALUES ('15', '1', '7859213249', '7689.00');
INSERT INTO `order` VALUES ('16', '1', '4598450230', '909.20');