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
    <el-col :span="4"><el-button type="primary" @click="selectOrder">搜索</el-button></el-col>
    <el-col :span="6"></el-col>
    <el-col :span="4"></el-col>
  </el-row>    
          </div>
          <el-table :data="orders">
            <el-table-column prop="orderId" label="ID"></el-table-column>
            <el-table-column prop="serialNumber" label="订单编号"></el-table-column>
            <el-table-column prop="createdAt" label="下单时间"></el-table-column>
            <el-table-column prop="totalPrice" label="订单金额"></el-table-column>
            <el-table-column prop="orderStatus" label="订单状态"></el-table-column>
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
    v-model="orderStatus"
    title="修改用户信息"
    width="70%"
  
  >
              <el-form   :model="order" label-width="160px">
                <el-form-item label="ID">
                  <el-input v-model="order.orderId" disabled></el-input>
                </el-form-item>
                <el-form-item label="User Type">                 
                    <el-select v-model="order.orderStatus"  placeholder="Non-member">
        <el-option label="下单" value="下单" />
        <el-option label="送达" value="送达" />
        <el-option label="已支付" value="已支付" />
        <el-option label="配送中" value="配送中" />
        <el-option label="暂停" value="暂停" />
      </el-select>
                </el-form-item>
              
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="orderStatus = false">Cancel</el-button>
                <el-button type="primary" @click="saveOrder">Save</el-button>
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
import {   orderAll,orderUpdate,orderDelete,orderSelect } from "@/service/orderManage";
import { ElMessage } from "element-plus";
import { tr } from "element-plus/es/locale";
import { ElMessageBox } from 'element-plus'
import { order }from '@/rules/index'
export default defineComponent({
    data(){
        return{
            orders: [] as order[],
            search: "搜索用户名",
            orderStatus: false,
            order: {} as order,
        }
    },
    name: "orderManage",
  components: {
    Aside,
    Header,
  },
    mounted(){
        orderAll().then((res) => {
            
          this.orders = res.data.data;
          
        }).catch((err) => {
            ElMessage.error("查询失败");
        });
    },
    methods:{
        selectOrder(){
            //根据输入用户名查询订单
            orderSelect(this.search).then((res) => {
                this.orders = res.data.data;
                ElMessage.success("查询成功");
              }).catch((err) => {
                ElMessage.error("查询失败");
              });
        },
        edit(index: number, row: order){
            this.order=row
            this.orderStatus=true
        },
        saveOrder(){
            //修改订单状态
            orderUpdate(this.order).then((res) => {
                ElMessage.success("修改成功");
                this.orderStatus=false
              }).catch((err) => {
                ElMessage.error("修改失败",err);
              });

        },
        del(index: number, row: order){
            //删除订单
            ElMessageBox.confirm('是否删除该订单?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                orderDelete(row.orderId).then((res) => {
                ElMessage.success("删除成功");      
                }).catch((err) => {
                  ElMessage.error("删除失败",err);
                });
              }).catch(() => {
                ElMessage({
                  type: 'info',
                  message: '已取消删除'
                });          
              });
        },
       
    }
})

</script>