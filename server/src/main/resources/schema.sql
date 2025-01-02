-- 用户表
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    phone VARCHAR(20),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    role VARCHAR(20) DEFAULT 'USER', -- USER 或 ADMIN
    balance DECIMAL(10,2) DEFAULT 0.00
);

-- 盲盒系列表
CREATE TABLE box_series (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status INT DEFAULT 1 -- 1:启用 0:禁用
);

-- 盲盒款式表
CREATE TABLE box_style (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    series_id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    probability DECIMAL(5,2) NOT NULL,
    image_url VARCHAR(255),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    status INT DEFAULT 1, -- 1:启用 0:禁用
    FOREIGN KEY (series_id) REFERENCES box_series(id)
);

-- 订单表
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL, -- PENDING, PAID, COMPLETED, CANCELLED
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- 订单详情表
CREATE TABLE order_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    box_style_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (box_style_id) REFERENCES box_style(id)
);

-- 评价表
CREATE TABLE review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    box_style_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    rating INT NOT NULL, -- 1-5星
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (box_style_id) REFERENCES box_style(id)
);

-- 轮播图表
CREATE TABLE banner (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    image_url VARCHAR(255) NOT NULL,
    link_url VARCHAR(255),
    sort_order INT DEFAULT 0,
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 用户登录日志表
CREATE TABLE login_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    ip_address VARCHAR(50),
    login_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- 创建充值记录表
CREATE TABLE recharge_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    amount DECIMAL(10,2),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- 创建支付记录表
CREATE TABLE payment_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    order_id BIGINT,
    amount DECIMAL(10,2),
    status VARCHAR(20), -- SUCCESS, FAILED
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- 初始数据

-- 插入用户数据
INSERT INTO user (username, password, nickname, phone, role, balance) VALUES
('admin', '$2a$10$X/hX4qvWzxwRqjwPz6tUG.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '管理员', '13800000000', 'ADMIN', 0.00),
('user1', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '张三', '13800000001', 'USER', 100.00),
('user2', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '李四', '13800000002', 'USER', 200.00),
('user3', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '王五', '13800000003', 'USER', 300.00),
('user4', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '赵六', '13800000004', 'USER', 400.00),
('user5', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '孙七', '13800000005', 'USER', 500.00),
('user6', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '周八', '13800000006', 'USER', 600.00),
('user7', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '吴九', '13800000007', 'USER', 700.00),
('user8', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '郑十', '13800000008', 'USER', 800.00),
('user9', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '冯十一', '13800000009', 'USER', 900.00),
('user10', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '陈十二', '13800000010', 'USER', 1000.00),
('user11', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '褚十三', '13800000011', 'USER', 1100.00),
('user12', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '卫十四', '13800000012', 'USER', 1200.00),
('user13', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '蒋十五', '13800000013', 'USER', 1300.00),
('user14', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '沈十六', '13800000014', 'USER', 1400.00),
('user15', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '韩十七', '13800000015', 'USER', 1500.00),
('user16', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '杨十八', '13800000016', 'USER', 1600.00),
('user17', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '朱十九', '13800000017', 'USER', 1700.00),
('user18', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '秦二十', '13800000018', 'USER', 1800.00),
('user19', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '尤二一', '13800000019', 'USER', 1900.00),
('user20', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '许二二', '13800000020', 'USER', 2000.00);

-- 插入盲盒系列数据
INSERT INTO box_series (name, description, status) VALUES
('动漫英雄', '收集你喜欢的动漫角色', 1),
('萌系宠物', '可爱的动物伙伴们', 1),
('科幻机甲', '未来世界的机械战士', 1),
('魔法少女', '充满魔力的美少女系列', 1),
('国风神话', '中国传统神话人物', 1),
('海洋生物', '神秘的海底世界', 1),
('太空探险', '浩瀚宇宙的奥秘', 1),
('童话世界', '经典童话故事角色', 1),
('运动健将', '热血体育竞技', 1),
('音乐精灵', '悦耳动听的音乐主题', 1),
('美食天地', '美味可口的食物造型', 1),
('职业达人', '各行各业的专业人士', 1),
('节日庆典', '欢乐节日主题系列', 1),
('环球旅行', '世界各地的特色元素', 1),
('四季物语', '春夏秋冬的自然变化', 1),
('都市生活', '现代都市的日常点滴', 1),
('奇幻生物', '想象中的神奇生物', 1),
('历史名人', '重要历史人物形象', 1),
('艺术殿堂', '艺术作品的精美再现', 1),
('未来科技', '充满科技感的未来物品', 1);

-- 插入盲盒款式数据
INSERT INTO box_style (series_id, name, description, price, stock, probability, image_url, status) VALUES
-- 动漫英雄系列
(1, '火影忍者-鸣人', '九尾查克拉模式', 29.90, 100, 0.05, '/uploads/naruto.png', 1),
(1, '海贼王-路飞', '四档形态', 29.90, 100, 0.05, '/uploads/luffy.png', 1),
(1, '七龙珠-悟空', '超级赛亚人', 29.90, 100, 0.05, '/uploads/goku.png', 1),
(1, '灌篮高手-樱木', '红头暴扣', 29.90, 100, 0.05, '/uploads/sakuragi.png', 1),
(1, '名侦探柯南', '真相只有一个', 29.90, 100, 0.05, '/uploads/conan.png', 1),

-- 萌系宠物系列
(2, '柴犬', '可爱的日本柴犬', 19.90, 200, 0.10, '/uploads/shiba.png', 1),
(2, '布偶猫', '优雅的布偶猫', 19.90, 200, 0.10, '/uploads/ragdoll.png', 1),
(2, '小仓鼠', '圆滚滚的仓鼠', 19.90, 200, 0.10, '/uploads/hamster.png', 1),
(2, '荷兰猪', '胖乎乎的豚鼠', 19.90, 200, 0.10, '/uploads/guineapig.png', 1),
(2, '小兔子', '蹦蹦跳跳的兔子', 19.90, 200, 0.10, '/uploads/rabbit.png', 1),

-- 科幻机甲系列
(3, '高达-自由', '自由高达', 39.90, 50, 0.03, '/uploads/freedom.png', 1),
(3, '高达-强袭', '强袭高达', 39.90, 50, 0.03, '/uploads/strike.png', 1),
(3, '高达-命运', '命运高达', 39.90, 50, 0.03, '/uploads/destiny.png', 1),
(3, '高达-正义', '正义高达', 39.90, 50, 0.03, '/uploads/justice.png', 1),
(3, '高达-强化自由', '强化自由高达', 39.90, 50, 0.03, '/uploads/sf.png', 1),

-- 魔法少女系列
(4, '小圆', '圆环之理', 29.90, 100, 0.05, '/uploads/madoka.png', 1),
(4, '小樱', '库洛魔法使', 29.90, 100, 0.05, '/uploads/sakura.png', 1),
(4, '美琪', '星光闪耀', 29.90, 100, 0.05, '/uploads/miki.png', 1),
(4, '小忧', '月光幻想', 29.90, 100, 0.05, '/uploads/yu.png', 1),
(4, '小焰', '时间守护者', 29.90, 100, 0.05, '/uploads/homura.png', 1);

-- 插入订单数据
INSERT INTO orders (user_id, total_amount, status) VALUES
(2, 29.90, 'COMPLETED'),
(2, 19.90, 'COMPLETED'),
(3, 39.90, 'COMPLETED'),
(4, 29.90, 'COMPLETED'),
(5, 19.90, 'COMPLETED'),
(6, 39.90, 'COMPLETED'),
(7, 29.90, 'COMPLETED'),
(8, 19.90, 'COMPLETED'),
(9, 39.90, 'COMPLETED'),
(10, 29.90, 'COMPLETED'),
(11, 19.90, 'COMPLETED'),
(12, 39.90, 'COMPLETED'),
(13, 29.90, 'COMPLETED'),
(14, 19.90, 'COMPLETED'),
(15, 39.90, 'COMPLETED'),
(16, 29.90, 'COMPLETED'),
(17, 19.90, 'COMPLETED'),
(18, 39.90, 'COMPLETED'),
(19, 29.90, 'COMPLETED'),
(20, 39.90, 'COMPLETED');

-- 插入订单详情数据
INSERT INTO order_detail (order_id, box_style_id, quantity, price) VALUES
(1, 1, 1, 29.90),
(2, 5, 1, 19.90),
(3, 9, 1, 39.90),
(4, 2, 1, 29.90),
(5, 6, 1, 19.90),
(6, 10, 1, 39.90),
(7, 3, 1, 29.90),
(8, 7, 1, 19.90),
(9, 11, 1, 39.90),
(10, 4, 1, 29.90),
(11, 8, 1, 19.90),
(12, 12, 1, 39.90),
(13, 1, 1, 29.90),
(14, 5, 1, 19.90),
(15, 9, 1, 39.90),
(16, 2, 1, 29.90),
(17, 6, 1, 19.90),
(18, 10, 1, 39.90),
(19, 3, 1, 29.90),
(20, 11, 1, 39.90);

-- 插入评价数据
INSERT INTO review (user_id, box_style_id, content, rating) VALUES
(2, 1, '很精致的手办，做工非常好！', 5),
(2, 5, '超可爱的柴犬，和图片一模一样', 5),
(3, 9, '高达的细节处理得很到位', 4),
(4, 2, '路飞的表情刻画得很生动', 5),
(5, 6, '布偶猫太可爱了，很满意', 5),
(6, 10, '强袭高达的涂装很精美', 5),
(7, 3, '悟空的发型做得很帅气', 4),
(8, 7, '小仓鼠萌萌的，很喜欢', 5),
(9, 11, '命运高达的武器很酷', 5),
(10, 4, '樱木的动作很有张力', 4),
(11, 8, '荷兰猪圆滚滚的很可爱', 5),
(12, 12, '正义高达的配色很漂亮', 5),
(13, 1, '鸣人的九尾模式太帅了', 5),
(14, 5, '柴犬的表情很到位', 4),
(15, 9, '自由高达的翅膀很精致', 5),
(16, 2, '路飞的四档造型很独特', 5),
(17, 6, '布偶猫的毛发很柔顺', 5),
(18, 10, '强袭高达的动作很帅', 4),
(19, 3, '悟空的肌肉线条很完美', 5),
(20, 11, '命运高达的涂装很精美', 5);

-- 插入轮播图数据
INSERT INTO banner (image_url, link_url, sort_order, status) VALUES
('/uploads/banner1.png', '/pages/series/detail?id=1', 1, 1),
('/uploads/banner2.png', '/pages/series/detail?id=2', 2, 1),
('/uploads/banner3.png', '/pages/series/detail?id=3', 3, 1),
('/uploads/banner4.png', '/pages/series/detail?id=4', 4, 1),
('/uploads/banner5.png', '/pages/series/detail?id=5', 5, 1); 