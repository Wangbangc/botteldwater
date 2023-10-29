
import { Loading } from '@element-plus/icons-vue/dist/types';

import { Loading } from 'element-plus/es/components/loading/src/service';

import { Bottom } from '@element-plus/icons-vue/dist/types';
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
      <el-col :span="4"><el-button type="primary" @click="selectbottled">搜索</el-button></el-col>
      <el-col :span="6"></el-col>
      <el-col :span="4">  <el-button type="primary" @click="bottledAdd = true">添加用户</el-button></el-col>
    </el-row>    
  
            </div>
            <el-table :data="bottleds">
              <el-table-column prop="id" label="ID"></el-table-column>
              <el-table-column prop="brand" label="品牌名"></el-table-column>
              <el-table-column prop="categoryName" label="商品类别"></el-table-column>
              <el-table-column prop="price" label="价格"></el-table-column>
              <el-table-column prop="description" label="商品介绍"></el-table-column>
              <el-table-column label="操作">
                <template #default="scope">
                  <el-row>
      <el-col :span="12"><el-button type="primary"   @click="edit(scope.$index,scope.row)" >编辑</el-button></el-col>
      <el-col :span="12">  <el-button type="danger"  @click="del(scope.$index,scope.row)">删除</el-button></el-col>
    </el-row>         
                </template>
              </el-table-column>
            </el-table>
            <el-dialog v-model="bottledAdd" title="添加商品" width="70%">
            <el-form :model="bottled"  label-width="160px">
              <el-form-item label="品牌名">
                <el-input v-model="bottled.brand"></el-input>
              </el-form-item>
              <el-form-item label="商品类别">
                <el-input v-model="bottled.categoryName"></el-input>
              </el-form-item>
              <el-form-item label="价格">
                <el-input v-model="bottled.price"></el-input>
              </el-form-item>
              <el-form-item label="商品介绍">
                <el-input v-model="bottled.description"></el-input>
              </el-form-item>
              <el-form-item label="图片上传">               
                <el-input required @change="s($event)" id="image_uploads"
      name="image_uploads" accept=".jpg,.png" type="file"></el-input>
              </el-form-item>
            
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="addbottled = false">取消</el-button>
              <el-button type="primary" @click="addBottled">保存</el-button>
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
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { UploadProps, UploadUserFile,UploadInstance } from 'element-plus'
import { defineComponent } from "vue";
import Aside from "@/components/aside/index.vue";
import Header from "@/components/header/index.vue";
import{ bottledselectAll, bottledadd, bottleddelete, bottledupdate, bottledselect, bottledselectId} from '@/service/bottledManage'
import { tr } from "element-plus/es/locale";
import {bottleds,bottled }from '@/rules/index'


const uploadRef = ref<UploadInstance>()
    
  export default defineComponent({
  
    data() {
      return {
        search: "搜索商品名" as string,
        bottledAdd:false as boolean,
        bottleds: [] as bottleds[],
        bottled: {} as bottled,
        addbottled: {} as boolean,
      };
    },
    name: "home",
    components: {
      Aside,
      Header,
    },
    mounted() {
        bottledselectAll().then((res) => {
            console.log(res);
          this.bottleds = res.data.data;
          
        }).catch((err) => {
          console.log(err);
        });
    
    },

  
    methods: {
      s($event: any){
        console.dir($event);
        console.log($event);
     
        const file = $event.target.files
        this.bottled.imageData = file
      },
        //添加商品其中涉及图片上传
        addBottled(){
            console.log(this.bottled);
            bottledadd(this.bottled).then((res) => {
                if (res.data.success) {
                ElMessage.success(res.data.message);
                this.bottledAdd = false;
               
                } else {
                ElMessage.error(res.data.message);
                }
            }).catch((error) => {
                console.log(error);
            });
            },
        
    },
  });
  </script>