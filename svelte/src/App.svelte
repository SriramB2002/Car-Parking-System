<script>
	import { Router, Link, Route } from "svelte-navigator";
	import HomeLogin from "./HomeLogin.svelte";
	import AdminLogin from "./AdminLogin.svelte";
	import WorkerLogin from "./WorkerLogin.svelte";
	import UserLogin from "./UserLogin.svelte";
	import UserRegister from "./UserRegister.svelte";
	import AdminDashboard from "./AdminDashboard.svelte";
	import WorkerDashboard from "./WorkerDashboard.svelte";
	import UserDashboard from "./UserDashboard.svelte";
	import UserProfile from "./UserProfile.svelte";
</script>

<Router>
	<main>
		<Route path="/"><HomeLogin/></Route>
		<Route path="AdminLogin"><AdminLogin/></Route>
		<Route path="WorkerLogin"><WorkerLogin/></Route>
		<Route path="UserLogin"><UserLogin/></Route>
		<Route path="UserRegister"><UserRegister/></Route>
		<Route path="AdminDashboard"><AdminDashboard/></Route>
		<Route path="WorkerDashboard"><WorkerDashboard/></Route>
		<Route path="UserDashboard"><UserDashboard/></Route>
		<Route path="UserProfile"><UserProfile/></Route>
	</main>
</Router>

<style>
	main {
		text-align: center;
		/* padding: 1em; */
		margin: 0 auto;
	}
</style>