CREATE TABLE `ref_client` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `client_code` varchar(255) DEFAULT NULL,
                              `client_name` varchar(255) DEFAULT NULL,
                              `status` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `ref_client_address` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `address_line1` varchar(255) DEFAULT NULL,
                                      `address_line2` varchar(255) DEFAULT NULL,
                                      `client_code` varchar(255) DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

