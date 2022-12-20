ALTER TABLE `accountingSystem`.`house`
    ADD INDEX `street_id_idx` (`street_id` ASC) VISIBLE;
;
ALTER TABLE `accountingSystem`.`house`
    ADD CONSTRAINT `street_id`
        FOREIGN KEY (`street_id`)
            REFERENCES `accountingSystem`.`street` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;