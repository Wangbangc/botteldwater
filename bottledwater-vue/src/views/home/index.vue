<template>
  <div class="common-layout">
    <el-container class="ss">
      <el-header class="aa"><Header class="aa"></Header></el-header>
      <el-container>
        <el-aside width="200px"><Aside></Aside></el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>
<style>
.ss{
  width: 100%;
}
.aa{
  width: 100%;
  background-color: #0a0a0a;
}
</style>
<script lang="ts">
import { defineComponent} from 'vue';

import Aside from '@/components/aside/index.vue';
import Header from '@/components/header/index.vue';

export default defineComponent({
  name: 'home',
  components: {
    Aside,
    Header
  },
});
</script>