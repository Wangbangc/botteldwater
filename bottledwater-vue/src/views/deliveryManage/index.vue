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
              placeholder=""
              v-model="search"
            ></el-input></el-col>
    <el-col :span="4"><el-button type="primary" @click="selectdelivery">搜索</el-button></el-col>
    <el-col :span="6"></el-col>
    <el-col :span="4">  <el-button type="primary" @click="adddelivery = true">添加用户</el-button></el-col>
  </el-row>    

          </div>
          <el-table :data="deliverys">
            <el-table-column prop="id" label="ID"></el-table-column>
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop=" realName" label="真实姓名"></el-table-column>
            <el-table-column prop="电话" label="电话"></el-table-column>
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
    v-model="adddelivery"
    title="添加配送员"
    width="70%"
  
  >
              <el-form   :model="delivery" label-width="160px">
            
                <el-form-item label="用户名">
                  <el-input v-model="delivery.username"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                  <el-input v-model="delivery.password"></el-input>
                </el-form-item>
                <el-form-item label="电话">
                  <el-input v-model="delivery.phone"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名">
                  <el-input v-model="delivery.realName"></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                  <el-input v-model="delivery.idNumber"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="adddelivery = false">Cancel</el-button>
                <el-button type="primary" @click="addDelivery">Save</el-button>
              </div>
            </el-dialog>
          <el-dialog
    v-model="updateDelivery"
    title="修改用户信息"
    width="70%"
  
  >
              <el-form   :model="delivery" label-width="160px">
                <el-form-item label="ID">
                  <el-input v-model="delivery.id" disabled></el-input>
                </el-form-item>
                <el-form-item label="用户名">
                  <el-input v-model="delivery.username"></el-input>
                </el-form-item>
                <el-form-item label="电话">
                  <el-input v-model="delivery.phone"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名">
                  <el-input v-model="delivery.realName"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="updateDelivery = false">Cancel</el-button>
                <el-button type="primary" @click="saveDelivery">Save</el-button>
              </div>
            </el-dialog>
        </el-main>
        </el-container>
    </el-container>
    </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import Aside from "@/components/aside/index.vue";
import Header from "@/components/header/index.vue";
import { ElMessage } from "element-plus";
import { tr } from "element-plus/es/locale";
import { ElMessageBox } from 'element-plus'
import { rulesuser }from '@/rules/index'
import {  deliveryselAll,deliveryadd,deliverydelete,deliveryupdate,deliveryselect } from '@/service/deliveryManage'
import{deliverys} from '@/rules/index'
export default defineComponent({
    data() {
        return {
            deliverys: [] as deliverys[],
            search: "搜索配送员名或手机号",
            updateDelivery: false,
            delivery: {} as deliverys,
            adddelivery: false,
        };
    },
    name: "deliveryManage",
    components: {
      Aside,
      Header,
    },
    mounted() {
        deliveryselAll().then((res) => {
            console.log(res);
          this.deliverys = res.data.data;
          
        }).catch((err) => {
          console.log(err);
        });
    
    },
    methods:{
        del(index: number, row: deliverys){
            ElMessageBox.confirm('是否删除该配送员?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                deliverydelete(row.id).then((res) => {
                    console.log(res);
                  this.deliverys = res.data.data;
                  
                }).catch((err) => {
                  console.log(err);
                });
              }).catch(() => {
                ElMessage({
                  type: 'info',
                  message: '已取消删除'
                });          
              });
        },
        edit(index: number, row: deliverys){
            this.delivery = row;
            this.updateDelivery = true;
        },
        saveDelivery(){
            deliveryupdate(this.delivery).then((res) => {
                console.log(res);
                if(res.data.success){
                    ElMessage.success("修改成功");
                    this.updateDelivery = false;}    
            }).catch((err) => {
             ElMessage.error("修改失败");
            });
        },
        selectdelivery(){
            deliveryselect(this.search).then((res) => {
              if(res.data.success){
                this.deliverys = res.data.data;
              }                            
            }).catch((err) => {
              ElMessage.error("查询失败");
            });
        },
        addDelivery(){
            deliveryadd(this.delivery).then((res) => {
                console.log(res);
                if(res.data.success){
                  if (res.data.code ==1) {
                    ElMessage.error(res.data.msg);
                  }
                  if (res.data.code ==2) {
                    ElMessage.error(res.data.msg);
                  }
                  if (res.data.code ==3) {
                    ElMessage.error(res.data.msg);
                  }
                  if (res.data.code ==4) {
                    ElMessage.error(res.data.msg);
                  }
                  if (res.data.code ==0) {
                    ElMessage.success("添加成功");
                    this.adddelivery = false;}    
                  }
            }).catch((err) => {
             ElMessage.error("添加失败");
            });
        }
    }
   
})


</script>
