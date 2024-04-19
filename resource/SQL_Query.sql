-- Make Table
	CREATE TABLE Contracts
	(
		WORD VARCHAR(50) primary key,
		MEAN VARCHAR(255),
		IPA VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
		GENRE VARCHAR(10),
		CURRENT_STATUS CHAR(1) 
	);

INSERT INTO Contracts 
	(WORD, MEAN, IPA, GENRE, CURRENT_STATUS) 
VALUES
('abide by', 'Tuân theo', 'əˈbaɪd', 'v', 'N'),
('agreement', 'Sự thỏa thuận, hợp đồng', 'əˈɡriː.mənt', 'n', 'N'),
('assurance', 'Sự đảm bảo, bảo hiểm', 'əˈʃɔː.rəns', 'n', 'N'),
('determine', 'Xác định, quyết định', 'dɪˈtɜː.mɪn', 'v', 'N'),
('provision', 'Sự dạy dỗ, dự phòng', 'prəˈvɪʒ.ən', 'n', 'N'),
('establish', 'Thành lập, củng cố', '/ɪˈstæb.lɪʃ', 'v', 'N'),
('obligate', 'bắt buộc, ép buộc', 'ˈɒb.lɪ.ɡeɪt', 'v', 'N'),
('resolve', 'Giải quyết', '/rɪˈzɒlv', 'v', 'N'),
('specific', 'Rõ ràng, cụ thể', 'spəˈsɪf.ɪk', 'adj', 'N');
-- TRUNCATE contracts

CREATE TABLE Marketing
(
	WORD VARCHAR(50) primary key,
    MEAN VARCHAR(255),
    IPA VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    GENRE VARCHAR(10),
    CURRENT_STATUS CHAR(1) 
);

INSERT INTO Marketing
	(WORD, MEAN, IPA, GENRE, CURRENT_STATUS) 
VALUES
('attract', 'Hấp dẫn, thu hút', 'əˈtrækt/', 'v', 'N'),
('compare', 'So sánh', 'kəmˈpeər', 'v', 'N'),
('competition', 'Sự cạnh tranh, cuộc thi', 'ˌkɒm.pəˈtɪʃ.ən', 'n', 'N'),
('consume', 'Sự tiêu thụ, sự phá hủy', 'kənˈsjuːm', 'v', 'N'),
('convince', 'Thuyết phục', 'kənˈvɪns', 'v', 'N'),
('inspiration', 'nguồn cảm hứng, sự thở', 'ɪn.spɪˈreɪ.ʃən', 'n', 'N'),
('Market', 'Chợ, thị trường', 'ˈmɑː.kɪt', 'n', 'N'),
('persuasion', 'Sự thuyết phục, sự tin tưởng', 'pəˈsweɪ.ʒən', 'n', 'N'),
('satisfaction', 'Sự thỏa mãn, sự đền đáp', 'ˌsæt.ɪsˈfæk.ʃən', 'n', 'N'),
('productive', 'Có năng suất, hữu ích', 'prəˈdʌk.tɪv', 'adj', 'N'); 

CREATE TABLE Warranties
(
	WORD VARCHAR(50) primary key,
    MEAN VARCHAR(255),
    IPA VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    GENRE VARCHAR(10),
    CURRENT_STATUS CHAR(1) 
);

INSERT INTO Warranties
	(WORD, MEAN, IPA, GENRE, CURRENT_STATUS) 
VALUES
('characteristic', 'Đặc điểm, tính chất đặc biệt', 'ˌkær.ək.təˈrɪs.tɪk', 'adj, n', 'N'),
('consequence', 'Hậu quả, kết quả', 'ˈkɒn.sɪ.kwəns', 'n', 'N'),
('consider', 'Cân nhắc, xem xét kỹ', 'kənˈsɪd.ər', 'v', 'N'),
('cover', 'bao gồm, bao phủ', 'ˈkʌv.ər', 'v', 'N'),
('expiration', 'Sự hết hạn, sự tắt thở', 'ˌek.spɪˈreɪ.ʃən', 'n', 'N'),
('frequently', 'một cách thường xuyên', 'friː.kwənt.li', 'adv', 'N'),
('Variety', 'Sự đa dạng, nhiều loại, nhiều thứ', 'vəˈraɪ.ə.ti/', 'n', 'N'),
('require', 'Đòi hỏi, yêu cầu', 'rɪˈkwaɪər', 'v', 'N'),
('reputation', 'Danh tiếng, sự nổi danh', 'ˌrep.jəˈteɪ.ʃən/', 'n', 'N'),
('protect', 'Bảo vệ, bảo hộ', '/prəˈtekt/', 'v', 'N'); 

CREATE TABLE Pharmacy
(
	WORD VARCHAR(50) primary key,
    MEAN VARCHAR(255),
    IPA VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    GENRE VARCHAR(10),
    CURRENT_STATUS CHAR(1) 
);

INSERT INTO Pharmacy
    (WORD, MEAN, IPA, GENRE, CURRENT_STATUS) 
VALUES
    ('prescription', 'Đơn thuốc', 'prɪˈskrɪp.ʃən', 'n', 'N'),
    ('dosage', 'Liều lượng', 'ˈdəʊ.sɪdʒ', 'n', 'N'),
    ('interaction', 'Tương tác', 'ˌɪn.təˈræk.ʃən', 'n', 'N'),
    ('side effect', 'Tác dụng phụ', 'saɪd ɪˈfekt', 'n', 'N'),
    ('generic medicine', 'Thuốc tương đương', 'dʒəˈner.ɪk ˈmed.ɪ.sɪn', 'n', 'N'),
    ('over-the-counter', 'Bán không cần đơn', 'ˌəʊ.və ðə ˈkaʊn.tər', 'adj', 'N'),
    ('compounding', 'Trộn chế phẩm', 'kəmˈpaʊndɪŋ', 'n', 'N'),
    ('refill', 'Tái cấp', 'riˈfɪl', 'v', 'N'),
    ('contraindication', 'Chống chỉ định', 'ˌkɒn.trəˌɪn.dɪˈkeɪ.ʃən', 'n', 'N'),
    ('antibiotic', 'Kháng sinh', 'ˌæn.tɪˈbaɪ.ɒt.ɪk', 'n', 'N'),
    ('anaphylaxis', 'Phản ứng dị ứng', 'ˌæn.ə.fɪˈlæk.sɪs', 'n', 'N'),
    ('ointment', 'Kem bôi', 'ˈɔɪnt.mənt', 'n', 'N'),
    ('drowsiness', 'Buồn ngủ', 'ˈdraʊ.zi.nəs', 'n', 'N'),
    ('expiration date', 'Ngày hết hạn', 'ˌek.spɪˈreɪ.ʃən deɪt', 'n', 'N'),
    ('dose', 'Liều lượng', 'dəʊs', 'n', 'N');
    
CREATE TABLE Computer
(
	WORD VARCHAR(50) primary key,
    MEAN VARCHAR(255),
    IPA VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    GENRE VARCHAR(10),
    CURRENT_STATUS CHAR(1) 
);

INSERT INTO Computer
    (WORD, MEAN, IPA, GENRE, CURRENT_STATUS) 
VALUES
    ('hardware', 'Phần cứng', 'ˈhɑːd.weər', 'n', 'N'),
    ('software', 'Phần mềm', 'ˈsɒftweər', 'n', 'N'),
    ('algorithm', 'Thuật toán', 'ˈæl.ɡə.rɪð.əm', 'n', 'N'),
    ('encryption', 'Mã hóa', 'ɪnˈkrɪp.ʃən', 'n', 'N'),
    ('firewall', 'Tường lửa', 'ˈfaɪə.wɔːl', 'n', 'N'),
    ('database', 'Cơ sở dữ liệu', 'ˈdeɪ.tə.beɪs', 'n', 'N'),
    ('RAM (Random Access Memory)', 'Bộ nhớ RAM', 'ræm', 'n', 'N'),
    ('CPU (Central Processing Unit)', 'Bộ xử lý trung tâm', 'ˌsiː.piːˈjuː', 'n', 'N'),
    ('network', 'Mạng', 'ˈnet.wɜːrk', 'n', 'N'),
    ('browser', 'Trình duyệt', 'ˈbraʊz.ər', 'n', 'N'),
    ('debugging', 'Gỡ lỗi', 'diˈbʌɡ.ɪŋ', 'n', 'N'),
    ('interface', 'Giao diện', 'ˈɪn.tə.feɪs', 'n', 'N'),
    ('malware', 'Phần mềm độc hại', 'ˈmæl.wɛər', 'n', 'N'),
    ('protocol', 'Giao thức', 'ˈprəʊ.tə.kɒl', 'n', 'N'),
    ('virtual reality', 'Thực tế ảo', 'ˈvɜː.tʃuəl riˈæl.ɪ.ti', 'n', 'N');

CREATE TABLE Electronics
(
	WORD VARCHAR(50) primary key,
    MEAN VARCHAR(255),
    IPA VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    GENRE VARCHAR(10),
    CURRENT_STATUS CHAR(1) 
);

INSERT INTO Electronics
    (WORD, MEAN, IPA, GENRE, CURRENT_STATUS) 
VALUES
    ('device', 'Thiết bị', 'dɪˈvaɪs', 'n', 'N'),
    ('circuit board', 'Bảng mạch điện', 'ˈsɜː.kɪt bɔːrd', 'n', 'N'),
    ('power supply', 'Nguồn điện', 'ˈpaʊər səˈplaɪ', 'n', 'N'),
    ('battery', 'Pin', 'ˈbæt.ər.i', 'n', 'N'),
    ('LED (Light Emitting Diode)', 'Đèn LED', 'led', 'n', 'N'),
    ('wire', 'Dây điện', 'waɪər', 'n', 'N'),
    ('connector', 'Đầu nối', 'kəˈnek.tər', 'n', 'N'),
    ('switch', 'Công tắc', 'swɪtʃ', 'n', 'N'),
    ('sensor', 'Cảm biến', 'ˈsensər', 'n', 'N'),
    ('input', 'Đầu vào', 'ˈɪn.pʊt', 'n', 'N'),
    ('output', 'Đầu ra', 'ˈaʊt.pʊt', 'n', 'N'),
    ('signal', 'Tín hiệu', 'ˈsɪɡ.nəl', 'n', 'N'),
    ('microcontroller', 'Vi điều khiển', 'ˌmaɪ.kroʊ.kənˈtroʊ.lər', 'n', 'N');
    
CREATE TABLE Hiring_and_Training
(
	WORD VARCHAR(50) primary key,
    MEAN VARCHAR(255),
    IPA VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    GENRE VARCHAR(10),
    CURRENT_STATUS CHAR(1) 
);

INSERT INTO Hiring_and_Training
    (WORD, MEAN, IPA, GENRE, CURRENT_STATUS) 
VALUES
    ('recruitment', 'Tuyển dụng', 'rɪˈkruːt.mənt', 'n', 'N'),
    ('candidate', 'Ứng viên', 'ˈkændɪdeɪt', 'n', 'N'),
    ('interview', 'Phỏng vấn', 'ˈɪn.tə.vjuː', 'n', 'N'),
    ('resume', 'Sơ yếu lý lịch', 'ˈrez.ə.meɪ', 'n', 'N'),
    ('job description', 'Mô tả công việc', 'dɪˈskrɪp.ʃən', 'n', 'N'),
    ('orientation', 'Đào tạo hướng dẫn', 'ˌɔːriənˈteɪʃən', 'n', 'N'),
    ('onboarding', 'Quá trình đào tạo cho nhân viên mới', 'ˈɒnˌbɔːrdɪŋ', 'n', 'N'),
    ('training program', 'Chương trình đào tạo', 'ˈtreɪnɪŋ ˈproʊ.ɡræm', 'n', 'N'),
    ('mentorship', 'Hướng dẫn', 'ˈmen.tər.ʃɪp', 'n', 'N'),
    ('skill development', 'Phát triển kỹ năng', 'skɪl dɪˈvel.əpmənt', 'n', 'N'),
    ('evaluation', 'Đánh giá', 'ɪˌvæl.juˈeɪʃən', 'n', 'N'),
    ('probation period', 'Thời gian thử việc', 'proʊˈbeɪʃən ˈpɪriəd', 'n', 'N'),
    ('performance review', 'Đánh giá hiệu suất', 'pəˈfɔːrməns rɪˈvjuː', 'n', 'N'),
    ('professional development', 'Phát triển nghề nghiệp', 'prəˈfeʃənl dɪˈveləpmənt', 'n', 'N'),
    ('career advancement', 'Thăng tiến sự nghiệp', 'kəˈrɪr ədˈvæns.mənt', 'n', 'N');

CREATE TABLE Applying_and_Interviewing
(
	WORD VARCHAR(50) primary key,
    MEAN VARCHAR(255),
    IPA VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    GENRE VARCHAR(10),
    CURRENT_STATUS CHAR(1) 
);

INSERT INTO Applying_and_Interviewing
    (WORD, MEAN, IPA, GENRE, CURRENT_STATUS) 
VALUES
    ('application', 'Đơn xin việc', 'ˌæplɪˈkeɪʃən', 'n', 'N'),
    ('cover letter', 'Thư xin việc', 'ˈkʌv.ər ˈlet.ər', 'n', 'N'),
    ('resume', 'Sơ yếu lý lịch', 'ˈrez.ə.meɪ', 'n', 'N'),
    ('job interview', 'Phỏng vấn việc làm', 'ʤɒb ˈɪn.tə.vjuː', 'n', 'N'),
    ('candidate', 'Ứng viên', 'ˈkændɪdeɪt', 'n', 'N'),
    ('qualification', 'Bằng cấp, kỹ năng', 'ˌkwɒlɪfɪˈkeɪʃən', 'n', 'N'),
    ('experience', 'Kinh nghiệm làm việc', 'ɪkˈspɪəriəns', 'n', 'N'),
    ('reference', 'Tham khảo', 'ˈrɛfərəns', 'n', 'N'),
    ('HR (Human Resources)', 'Nhân sự', 'eɪʧ ɑːr', 'n', 'N'),
    ('skill set', 'Bộ kỹ năng', 'skɪl sɛt', 'n', 'N'),
    ('strengths', 'Ưu điểm', 'strɛŋkθs', 'n', 'N'),
    ('weaknesses', 'Nhược điểm', 'ˈwiːknɪsɪz', 'n', 'N'),
    ('assessment', 'Đánh giá', 'əˈsɛsmənt', 'n', 'N'),
    ('panel interview', 'Phỏng vấn nhóm', 'ˈpænl ˈɪn.tə.vjuː', 'n', 'N'),
    ('behavioral interview', 'Phỏng vấn hành vi', 'bɪˈheɪvjərəl ˈɪn.tə.vjuː', 'n', 'N');

-- make saved_table and insert data
CREATE TABLE Saved_Table
(
	WORD VARCHAR(50) primary key,
    MEAN VARCHAR(255),
    IPA VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    GENRE VARCHAR(10),
    CURRENT_STATUS CHAR(1) 
);

INSERT INTO Saved_Table
    (WORD, MEAN, IPA, GENRE, CURRENT_STATUS) 
VALUES
    ('application', 'Đơn xin việc', 'ˌæplɪˈkeɪʃən', 'n', 'N'),
    ('cover letter', 'Thư xin việc', 'ˈkʌv.ər ˈlet.ər', 'n', 'N'),
	('resume', 'Sơ yếu lý lịch', 'ˈrez.ə.meɪ', 'n', 'N'),
    ('job description', 'Mô tả công việc', 'dɪˈskrɪp.ʃən', 'n', 'N'),
    ('orientation', 'Đào tạo hướng dẫn', 'ˌɔːriənˈteɪʃən', 'n', 'N'),
    ('connector', 'Đầu nối', 'kəˈnek.tər', 'n', 'N'),
    ('switch', 'Công tắc', 'swɪtʃ', 'n', 'N'),
    ('sensor', 'Cảm biến', 'ˈsensər', 'n', 'N'),
    ('prescription', 'Đơn thuốc', 'prɪˈskrɪp.ʃən', 'n', 'N'),
    ('dosage', 'Liều lượng', 'ˈdəʊ.sɪdʒ', 'n', 'N'),
    ('interaction', 'Tương tác', 'ˌɪn.təˈræk.ʃən', 'n', 'N'),
    ('require', 'Đòi hỏi, yêu cầu', 'rɪˈkwaɪər', 'v', 'N'),
	('reputation', 'Danh tiếng, sự nổi danh', 'ˌrep.jəˈteɪ.ʃən/', 'n', 'N'),
	('protect', 'Bảo vệ, bảo hộ', '/prəˈtekt/', 'v', 'N'); 

