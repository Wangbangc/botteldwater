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
              placeholder="搜索订单号或者配送员名"
              v-model="search"
            ></el-input></el-col>
    <el-col :span="4"><el-button type="primary" @click="selectDeliveryOrder">搜索</el-button></el-col>
    <el-col :span="6"></el-col>
    <el-col :span="4">  <el-button type="primary" @click="adddeliveryOrder = true">添加用户</el-button></el-col>
  </el-row>    

            </div>
            <el-table :data="deliveryOrders">
            <el-table-column prop="deliveryAssignmentId" label="ID"></el-table-column>
            <el-table-column prop="serialNumber" label="订单编号"></el-table-column>
            <el-table-column prop="userName" label="买方用户名"></el-table-column>
            <el-table-column prop="deliveryPersonName" label="配送员"></el-table-column>
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
    v-model="adddeliveryOrder"
    title="分配配送员"
    width="70%"
  
  >
              <el-form   :model="deliveryOrder" label-width="160px">
            
                <el-form-item label="配送员姓名">
                  <el-input v-model="deliveryOrder.deliveryPersonName"></el-input>
                </el-form-item>
                <el-form-item label="订单编号">
                  <el-input v-model="deliveryOrder.serialNumber"></el-input>
                </el-form-item>
                
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="adddeliveryOrder = false">Cancel</el-button>
                <el-button type="primary" @click="savedeliveryOrder">Save</el-button>
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
import {doAdd,doSelectAll } from '@/service/DeliveryOrderManage'
import{deliveryOrderAdd,DeliveryPersonAndOrderRelationshipTable} from '@/rules/index'
export default defineComponent({
    data() {
        return {
            deliveryOrders: [] as DeliveryPersonAndOrderRelationshipTable[],
            search: "",
            updateDeliveryOrder: false,
            deliveryOrder: {} as deliveryOrderAdd,
            adddeliveryOrder: false ,
        };
    },
    name: "DeliveryOrderManage",
    components: {
      Aside,
      Header,
    },
    mounted() {
      console.log("666");
      //查询所有订单关系表
      doSelectAll().then((res) => {
        this.deliveryOrders = res.data.data;
      }).catch((err) => {
        ElMessage.error("查询失败");
      });
    
    },
    methods:{
        selectDeliveryOrder(){

        },
        savedeliveryOrder(){
          //添加配送员关系
          doAdd(this.deliveryOrder).then((res) => {
          
            if(res.data.success){
            
              ElMessage.success("添加成功");
              this.adddeliveryOrder = false;
            }else{
              ElMessage.error(res.data.message);
            }
          }).catch((err) => {
            ElMessage.error("添加失败");
          });
        },
        edit(index: number, row: DeliveryPersonAndOrderRelationshipTable){

        },
        del(index: number, row: DeliveryPersonAndOrderRelationshipTable){

        }
    }
})
</script>