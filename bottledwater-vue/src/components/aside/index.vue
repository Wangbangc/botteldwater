<template>
  <div class="asi" :style="{
      position: 'relative',
      minWidth: isCollapse ? '240px' : '240px',
      height: 'calc(100vh )',
    }" >
    <el-row class="bom">
      <el-col :span="12"> <div>
        <el-button class="logout" @click="Coll"  :icon="View" circle />
      </div></el-col>
      <el-col :span="12">  <div>
        <el-button class="logout" @click="logout" :icon="SwitchButton" circle />
      </div></el-col>
    </el-row>
        <el-aside width="240px" class="aside">
          <el-menu
              background-color="#e6e9ee"
              default-active="2"
              class="el-menu-vertical-demo"
              :collapse="isCollapse"
          >
            <el-sub-menu index="1">
              <template #title>
                <el-icon><Notebook /></el-icon>
                <span> 商品管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1"><el-button
                    key="plain"
                    type=""
                    link
                  @click="bottledManage"
                >商品信息</el-button></el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="2">
              <template #title>
                <el-icon><UserFilled /></el-icon>
                <span>  信息管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="2-1"><el-button
                    key="plain"
                    type=""
                    link
                    @click="userInfo"
                >用户信息管理</el-button></el-menu-item>
              </el-menu-item-group>

            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <el-icon><TakeawayBox /></el-icon>
                <span> 订单管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1"><el-button
                    key="plain"
                    type=""
                    link
                  @click="orderManage"
                >订单信息</el-button></el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group>
                <el-menu-item index="1-1"><el-button
                    key="plain"
                    type=""
                    link
                  @click="DeliveryOrderManage"
                >订单配送员分配</el-button></el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
            <el-sub-menu index="4">
              <template #title>
                <el-icon><Van /></el-icon>
                <span> 配送员管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1"><el-button
                    key="plain"
                    type=""
                    link
                  @click="deliveryManage"
                >配送员信息</el-button></el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
         </el-menu>
        </el-aside>

  </div>
</template>
<script  setup>
import {
  View,
 SwitchButton,
  Document,
  TakeawayBox,
  UserFilled,
  Van ,
  Notebook 
} from '@element-plus/icons-vue'
</script>
<script>
export default {
  data(){
    return{
      label:false,
      isCollapse:true,
    }
  },
  methods:{
    Coll(){if ( this.isCollapse){
      this.isCollapse=false;
    }else {
      this.isCollapse=true;
    }

    },
    logout(){
      this.$router.push('/')
    },
bottledManage(){
  this.$router.push('/bottledManage')
},
    userInfo(){
      // 跳转用户信息管理
      this.$router.push('/userManage')
    },
    deliveryManage(){
      // 跳转配送员信息管理
      this.$router.push('/deliveryManage')
    },
    orderManage(){
      this.$router.push('/orderManage')
    },
    DeliveryOrderManage(){
      this.$router.push('/DeliveryOrderManage')
    }
  }
}
</script>

<style>
.asi{
  height: calc(100vh - 64px);
  background-color: #e6e9ee;
}
.bom{

  justify-content: flex-end !important;
  padding: 10px!important;
}
.logout{
  background: transparent !important;
  border: none !important;
}
.aside{
  height: calc(100vh - 64px);
  background-color: #e6e9ee;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px;
  min-height: 400px;
  border-right: none;

}
.radio{
  background-color: #e6e9ee;
}
.radiobutton{

  background-color:  rgba(0, 0, 0, 0) ;/* 非选中时的背景色 */
  border: 1px solid  rgba(0, 0, 0, 0); /* 非选中时的边框样式 */
  cursor: pointer;
}
.radiobutton:active{
  background-color: rgba(0, 0, 0, 0); /* 非选中时的背景色 */
  border: 1px solid  rgba(0, 0, 0, 0); /* 非选中时的边框样式 */
}
</style>