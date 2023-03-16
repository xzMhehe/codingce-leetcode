package models

import (
	"github.com/astaxie/beego/orm"
	_ "github.com/go-sql-driver/mysql"
)

type Users struct { // 对应表中 users 的字段
	Id       int
	Name     string
	Password string
	Sex      int
	Role     *UserRole `orm:"rel(fk)"`
}

type UserRole struct { // 对应表中user_role表名
	Id    int
	Name  string   `orm:"column(role_name)"`
	Desc  string   `orm:"column(role_desc)"`
	Users []*Users `orm:"reverse(many)"`
}

func init() {
	// 需要在init中注册定义的model
	orm.RegisterModel(new(Users), new(UserRole))

	orm.Debug = true

	// 数据库
	orm.RegisterDriver("mysql", orm.DRMySQL)
	orm.RegisterDataBase("default", "mysql", "root:1234567890@/beego_one?charset=utf8")
}

func FindUsersByUserName(UserName string) (*Users, error) {
	o := orm.NewOrm() // 创建新的orm
	user := &Users{}
	o.QueryTable("Users").Filter("name", UserName).One(user)
	return user, nil
	// if err == orm.ErrNoRows {
	// 	fmt.Println("查询不到该账户")
	// } else if err == orm.ErrMissPK {
	// 	fmt.Println("找不到主键")
	// } else {
	// 	fmt.Println(user.Id, user.UserName)
	// }
}

func AllUsers() ([]*Users, error) {
	var u []*Users
	_, err := orm.NewOrm().QueryTable("users").RelatedSel().All(&u)
	return u, err
}

func FindRoleById(id int) *UserRole {
	r := &UserRole{}
	// 反向一对多
	o := orm.NewOrm()
	o.QueryTable("UserRole").Filter("id", id).RelatedSel().One(r)
	o.LoadRelated(r, "Users")

	return r
}
