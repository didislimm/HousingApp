ALTER TABLE `accountingSystem`.`flat`
    ADD INDEX `floor_id_idx` (`floor_id` ASC) VISIBLE;
;
ALTER TABLE `accountingSystem`.`flat`
    ADD CONSTRAINT `floor_id`
        FOREIGN KEY (`floor_id`)
            REFERENCES `accountingSystem`.`floor` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;