CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `age` int(11) NOT NULL,
  `classId` int(11) NOT NULL,
  `sex` varchar(16) NOT NULL,
  `dept` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

insert into student values(1,'lihang',18,1,'male','ITDept');
insert into student values(2,'zhangsan',20,2,'female','MathDept');
insert into student values(3,'lisi',22,1,'male','EnglishDept');
insert into student values(4,'wangwu',24,3,'female','MathDept');