CREATE TABLE `settings` (
	`settings_id`      INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`measure_duration` INT          NOT NULL,
	`date_created`     TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3),
	`date_modified`    TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3)
	ON UPDATE CURRENT_TIMESTAMP(3)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4;

CREATE TABLE `unit` (
	`unit_id`             INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`name`                VARCHAR(255) NOT NULL,
	`unit_of_measurement` VARCHAR(255) NOT NULL,
	`date_created`        TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3),
	`date_modified`       TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3)
	ON UPDATE CURRENT_TIMESTAMP(3)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4;

CREATE TABLE `location` (
	`location_id`   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`name`          VARCHAR(255) NOT NULL,
	`gps`           VARCHAR(255),
	`date_created`  TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3),
	`date_modified` TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3)
	ON UPDATE CURRENT_TIMESTAMP(3)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4;

CREATE TABLE `category` (
	`category_id`   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`name`          VARCHAR(255) NOT NULL,
	`date_created`  TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3),
	`date_modified` TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3)
	ON UPDATE CURRENT_TIMESTAMP(3)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4;

CREATE TABLE `location_category` (
	`location_id` INT NOT NULL,
	`category_id` INT NOT NULL,
	PRIMARY KEY (`location_id`, `category_id`),
	FOREIGN KEY (location_id) REFERENCES location (location_id),
	FOREIGN KEY (category_id) REFERENCES category (category_id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4;

CREATE TABLE `measuring_station` (
	`measuring_station_id` INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`hardware_id`          VARCHAR(255) NOT NULL UNIQUE,
	`settings_id`          INT          NOT NULL,
	`location_id`          INT          NOT NULL,
	`date_created`         TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3),
	`date_modified`        TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3)
	ON UPDATE CURRENT_TIMESTAMP(3),
	FOREIGN KEY (settings_id) REFERENCES settings (settings_id),
	FOREIGN KEY (location_id) REFERENCES location (location_id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4;


CREATE TABLE `measuring_data` (
	`measuring_data_id`    INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`value`                FLOAT        NOT NULL,
	`unit_id`              INT          NOT NULL,
	`measuring_station_id` INT          NOT NULL,
	`location_id`          INT          NOT NULL,
	`date_measured`        TIMESTAMP(3) NOT NULL,
	`date_created`         TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3),
	`date_modified`        TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3)
	ON UPDATE CURRENT_TIMESTAMP(3),
	FOREIGN KEY (measuring_station_id) REFERENCES measuring_station (measuring_station_id),
	FOREIGN KEY (unit_id) REFERENCES unit (unit_id),
	FOREIGN KEY (location_id) REFERENCES location (location_id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4;

CREATE TABLE `critical_value` (
	`critical_value_id` INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`location_id`       INT,
	`unit_id`           INT          NOT NULL,
	`min_value`         FLOAT        NOT NULL,
	`max_value`         FLOAT        NOT NULL,
	`date_created`      TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3),
	`date_modified`     TIMESTAMP(3) NOT NULL             DEFAULT CURRENT_TIMESTAMP(3)
	ON UPDATE CURRENT_TIMESTAMP(3),
	FOREIGN KEY (location_id) REFERENCES location (location_id),
	FOREIGN KEY (unit_id) REFERENCES unit (unit_id)
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8mb4;