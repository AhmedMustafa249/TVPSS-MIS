CREATE TABLE SchoolInformation (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   school_name VARCHAR(255) NOT NULL,
                                   address VARCHAR(255) NOT NULL,
                                   brand_name VARCHAR(255),
                                   youtube_channel_link VARCHAR(2083),
                                   email VARCHAR(255) NOT NULL,
                                   contact_information VARCHAR(255) NOT NULL,
                                   school_code VARCHAR(50) NOT NULL,
                                   person_in_charge VARCHAR(255) NOT NULL,
                                   in_school_recording BOOLEAN DEFAULT FALSE,
                                   outside_recording BOOLEAN DEFAULT FALSE,
                                   agency_collaboration BOOLEAN DEFAULT FALSE,
                                   green_screen BOOLEAN DEFAULT FALSE,
                                   verified BOOLEAN DEFAULT FALSE
);
