
<template>
    <div class="common-layout">
        <el-container class="ss">
            <el-header class="aa"><Header class="aa"></Header></el-header>
            <el-container>
                <el-aside width="200px"><Aside></Aside></el-aside>
                <el-main>
                    <div class="admin-form">
                        <el-form :model="admin" label-width="100px">
                            <el-form-item label="邮箱">
                                <el-input v-model="admin.email"></el-input>
                            </el-form-item>
                            <el-form-item label="密码">
                                <el-input v-model="admin.password"></el-input>
                            </el-form-item>
                            <el-form-item label="手机号">
                                <el-input v-model="admin.phone"></el-input>
                            </el-form-item>
                            <el-form-item label="用户名">
                                <el-input v-model="admin.username"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="updateadmin">Save</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { adminselect, adminUpdate  } from '@/service/index';
import Aside from '@/components/aside/index.vue';
import Header from '@/components/header/index.vue';
import { ElMessage } from 'element-plus'
export interface Admin {
    email: string;
    password: string;
    phone: string;
    username: string;
}

export default defineComponent({
    name: 'AdminPage',
    components: {
        Aside,
        Header,
    },
    data() {
        return {
            admin: {} as Admin,
        };
    },
    methods: {
        updateadmin() {
            adminUpdate(this.admin).then((res) => {
                console.log(res);
               if(res.code === 0) {
                ElMessage.success('更新信息成功,请重新登录');
               } if (res.code === 2) {
                ElMessage.error('用户名已存在  请重新输入');
                
               }else {
                ElMessage.error('更新失败');
               }
            });
        },
    },
    mounted() {
      
        adminselect().then((res) => {
            this.admin = res.data;
        }).catch((err) => {
            console.log(err);
        })
    },
});
</script>

<style scoped>
.admin-form {
    margin: 20px;
}
</style>
