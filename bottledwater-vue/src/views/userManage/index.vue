<template>
  <div class="common-layout">
    <el-container class="ss">
      <el-header class="aa">
        <Header class="aa"></Header>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <Aside></Aside>
        </el-aside>
        <el-main>
                <div class="search">
                  <el-row>
    <el-col :span="10"> <el-input
              placeholder="搜索用户名或邮箱手机号"
              v-model="search"
            ></el-input></el-col>
    <el-col :span="4"><el-button type="primary" @click="selectUser">搜索</el-button></el-col>
    <el-col :span="6"></el-col>
    <el-col :span="4">  <el-button type="primary" @click="useradd = true">添加用户</el-button></el-col>
  </el-row>    

          </div>
          <el-table :data="users">
            <el-table-column prop="id" label="ID"></el-table-column>
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="phone" label="电话"></el-table-column>
            <el-table-column prop="userType" label="用户类型"></el-table-column>
            <el-table-column prop="companyInfo" label="companyInfo"></el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-row>
    <el-col :span="12"><el-button type="primary"   @click="edit(scope.$index,scope.row)" >编辑</el-button></el-col>
    <el-col :span="12">  <el-button type="danger"  @click="del(scope.$index,scope.row)">删除</el-button></el-col>
  </el-row>         
              </template>
            </el-table-column>
          </el-table>
          <el-dialog
    v-model="dialogVisible"
    title="修改用户信息"
    width="70%"
  
  >
              <el-form   :model="user" label-width="160px">
                <el-form-item label="ID">
                  <el-input v-model="user.id" disabled></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                  <el-input v-model="user.username"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                  <el-input v-model="user.email"></el-input>
                </el-form-item>
                <el-form-item label="电话">
                  <el-input v-model="user.phone"></el-input>
                </el-form-item>
                <el-form-item label="用户类型">                 
                    <el-select v-model="user.userType"  placeholder="Non-member">
        <el-option label="普通用户" value="Non-member" />
        <el-option label="企业用户" value="Member" />
      </el-select>
                </el-form-item>
                <el-form-item label="Company Info">
                  <el-input v-model="user.companyInfo"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">Cancel</el-button>
                <el-button type="primary" @click="saveUser">Save</el-button>
              </div>
            </el-dialog>
            <el-dialog v-model="useradd" title="添加用户" width="70%">
            <el-form :model="user" :rules="rulesuser" label-width="160px">
              <el-form-item label="用户名">
                <el-input v-model="user.username"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="user.password"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input v-model="user.email"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <el-input v-model="user.phone"></el-input>
              </el-form-item>
              <el-form-item label="用户类型">
                <el-select v-model="user.userType" placeholder="Non-member">
                  <el-option label="普通用户" value="Non-member" />
                  <el-option label="企业用户" value="Member" />
                </el-select>
              </el-form-item>
              <el-form-item label="公司信息">
                <el-input v-model="user.companyInfo"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取消</el-button>
              <el-button type="primary" @click="addUser">保存</el-button>
            </div>
          </el-dialog>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<style>
.dialog {
  width: 400px;
  height: 500px;
  margin: 20px 0;
  padding: 20px;
  border: 1px solid #ebeef5;
  background-color: #16c7be;
  z-index: 999;
}
</style>
<script lang="ts">

import { defineComponent } from "vue";
import Aside from "@/components/aside/index.vue";
import Header from "@/components/header/index.vue";
import { userselectAll,usersel ,userupdate,userdelete,useradd,userselect } from "@/service/userManage";
import { ElMessage } from "element-plus";
import { tr } from "element-plus/es/locale";
import { ElMessageBox } from 'element-plus'
import { rulesuser }from '@/rules/index'

export interface Datum {
  companyInfo: null;
  email: string;
  id: number;
  phone: string;
  username: string;
  userType: null;
  password: string;
  [property: string]: any;
}

export default defineComponent({
  data() {
    return {
      users: [] as Datum[],
      user: {} as Datum,
      dialogVisible: false as boolean,
      search: "搜索用户名或邮箱手机号" as string,
      useradd:false as boolean,
      rulesuser: rulesuser,
    };
  },
  name: "home",
  components: {
    Aside,
    Header,
  },
  mounted() {
    userselectAll().then((res) => {
  if (res.data.success) {  
    this.users = res.data.data;
    console.log(this.users);
  } else {
    ElMessage.error(res.message);
  }
}).catch((error) => {
  ElMessage.error("网络请求失败");
  console.error(error);
});

  },

  methods: {
    edit(index: number, row: Datum) {
      usersel(row.id).then((res) => {
        if (res.data.success) {
          //把data存在user里面然后弹出一个表单窗口可以修改值
          this.user = res.data.data;
          this.dialogVisible = true;       
        } else {
          ElMessage.error(res.data.message);
        }
      }).catch((error) => {
        ElMessage.error("网络请求失败");
        console.error(error);
      });
    },
    //删除用户
    del(index: number, row: Datum) {
      console.log(row.id);
      ElMessageBox.confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        userdelete(row.id).then((res) => {
          if (res.data.success) {
            ElMessage.success("删除成功");
            this.users.splice(index, 1);
          } else {
            ElMessage.error(res.data.message);
          }
        }).catch((error) => {
          ElMessage.error("删除失败");
          console.error(error);
        });
      }).catch(() => {
        ElMessage.info('已取消删除');
      });
    },
    saveUser() {
             //保存用户信息
              userupdate(this.user).then((res) => {
                if (res.data.success) {
                  this.dialogVisible = false;
                  ElMessage.success("修改成功");
                } else {
                  ElMessage.error(res.data.message);
                }
              }).catch((error) => {
                ElMessage.error("修改失败");
                console.error(error);
              });
              
    },
    //模糊查询用户
    selectUser(){
      userselect(this.search).then((res) => {
        if (res.data.success) {
          this.users = res.data.data;
          console.log(this.users);
        }
        if(res.data.code==2){
         this.users=[]
         
        }
       
      }).catch((error) => {
        ElMessage.error("网络请求失败");
        console.error(error);
      });
    },
    //添加用户
    addUser(){
      useradd(this.user).then((res) => {
        if (res.data.success) {
          this.dialogVisible = false;
          ElMessage.success("添加成功");
        } else {
          ElMessage.error(res.data.message);
        }
      }).catch((error) => {
        ElMessage.error("添加失败");
        console.error(error);
      });
    }
  
  },
});
</script>