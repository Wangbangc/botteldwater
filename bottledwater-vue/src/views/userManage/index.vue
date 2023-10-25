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
          <el-table :data="users">
            <el-table-column prop="id" label="ID"></el-table-column>
            <el-table-column prop="username" label="Username"></el-table-column>
            <el-table-column prop="email" label="Email"></el-table-column>
            <el-table-column prop="phone" label="Phone"></el-table-column>
            <el-table-column prop="userType" label="userType"></el-table-column>
            <el-table-column prop="companyInfo" label="companyInfo"></el-table-column>
          </el-table>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Aside from "@/components/aside/index.vue";
import Header from "@/components/header/index.vue";
import { userselectAll } from "@/service/userManage";
import { ElMessage } from "element-plus";

export interface Datum {
  companyInfo: null;
  email: string;
  id: number;
  phone: string;
  username: string;
  userType: null;
  [property: string]: any;
}

export default defineComponent({
  data() {
    return {
      users: [] as Datum[],
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
      });
  },

  methods: {
   
    
   
  },
});
</script>