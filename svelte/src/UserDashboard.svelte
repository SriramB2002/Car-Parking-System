<script>
import { navigate } from "svelte-navigator";


    import { login } from "./stores";

    const signOut = () => {
        const auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(() => {
            console.log("SIGNED OUT");
            login.set("");
            navigate("UserLogin");
        });
    }
    
</script>

<main>
    <p>{$login}</p>
    <button on:click={signOut}>Sign Out</button>
</main>

<style>

</style>