ALTER TABLE `accountingSystem`.`floor`
    ADD INDEX `house_id_idx` (`house_id` ASC) VISIBLE;
;
ALTER TABLE `accountingSystem`.`floor`
    ADD CONSTRAINT `house_id`
        FOREIGN KEY (`house_id`)
            REFERENCES `accountingSystem`.`house` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
