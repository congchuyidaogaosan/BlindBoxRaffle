<template>
  <div class="layout">
    <el-container>
      <el-aside width="200px">
        <el-menu
          :default-active="activeMenu"
          :router="true"
          class="el-menu-vertical"
          background-color="#304156"
          text-color="#bfcbd9"
          active-text-color="#409EFF"
        >
          <el-menu-item index="/dashboard">
            <i class="el-icon-s-home"></i>
            <span>控制台</span>
          </el-menu-item>
          
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-box"></i>
              <span>盲盒管理</span>
            </template>
            <el-menu-item index="/series">系列管理</el-menu-item>
            <el-menu-item index="/styles">款式管理</el-menu-item>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-s-order"></i>
              <span>订单管理</span>
            </template>
            <el-menu-item index="/orders">订单列表</el-menu-item>

          </el-submenu>

          <el-menu-item index="/users">
            <i class="el-icon-user"></i>
            <span>用户管理</span>
          </el-menu-item>

          <el-menu-item index="/statistics">
            <i class="el-icon-data-line"></i>
            <span>数据统计</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container class="main-container">
        <el-header>
          <div class="header-left">
            <i class="el-icon-s-fold"></i>
          </div>
          <div class="header-right">
            <span>{{ userInfo?.username }}</span>
            <el-dropdown trigger="click" @command="handleCommand">
              <span class="el-dropdown-link">
                <i class="el-icon-setting"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="password">修改密码</el-dropdown-item>
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'LayoutView',
  computed: {
    userInfo() {
      return this.$store.getters['user/getUserInfo']
    },
    activeMenu() {
      return this.$route.path
    }
  },
  methods: {
    handleCommand(command) {
      if (command === 'logout') {
        this.$store.dispatch('user/logout')
        this.$router.push('/login')
      } else if (command === 'password') {
        // TODO: 实现修改密码功能
      }
    }
  }
}
</script>

<style scoped>
.layout {
  min-height: 100vh;
}

.el-aside {
  background-color: #304156;
  height: 100vh;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 10;
}

.el-menu {
  border-right: none;
}

.el-menu-vertical {
  height: 100%;
}

.main-container {
  margin-left: 200px;
  min-height: 100vh;
}

.el-header {
  background-color: #fff;
  color: #333;
  line-height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);
  position: fixed;
  width: calc(100% - 200px);
  top: 0;
  z-index: 9;
}

.header-left {
  display: flex;
  align-items: center;
}

.header-left i {
  font-size: 20px;
  cursor: pointer;
  margin-right: 20px;
}

.header-right {
  display: flex;
  align-items: center;
}

.header-right span {
  margin-right: 15px;
}

.el-dropdown-link {
  cursor: pointer;
  font-size: 20px;
}

.el-main {
  background-color: #f0f2f5;
  padding: 20px;
  margin-top: 60px;
  min-height: calc(100vh - 60px);
}
</style> 