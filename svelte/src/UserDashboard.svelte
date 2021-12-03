<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
import { navigate } from "svelte-navigator";
import { Navbar, NavbarBrand, Nav, NavItem, Card, CardHeader, CardBody, CardText } from "sveltestrap/src";
    import { login } from "./stores";

    const signOut = () => {
        const auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(() => {
            console.log("SIGNED OUT");
            login.set("");
            navigate("UserLogin");
        });
    }
    
    const nav = () => {
        navigate("UserProfile");
    }

    let bool = 0;
    const check = () => {
        bool = true;
    }
    let choice;
</script>

<main>
    <Navbar color="dark" dark expand="md">
        <NavbarBrand href="/UserDashboard">Car Parking System</NavbarBrand>
        <Nav class="ms-auto" navbar>
            <NavItem>
                <button class="orange" on:click={nav}>View Profile</button>
            </NavItem>
            <NavItem>
                <button class="green" on:click={signOut}>Sign Out</button>
            </NavItem>
        </Nav>
    </Navbar>
    <p>{$login}</p>
    <h1>Book your slot here</h1>
    <form on:submit|preventDefault={check}>
        <h6>Location</h6>
        <select bind:value={choice} required>
            <option></option>
            <option>Entrance Gate</option>
            <option>Connaught Place</option>
            <option>Academics Block</option>
            <option>Student Activity Center</option>
        </select>
        <br><br>
        <h6>Date</h6>
        <input type="date" required><br><br>
        <h6>Checkin</h6>
        <input type="time" required><br><br>
        <h6>Checkout</h6>
        <input type="time" required><br><br>
        <button class="blue" type="submit">Find Slots</button>
    </form>
    {#if bool}
        {#if choice == "Entrance Gate"}
            <div class="flex">
                <Card class="col-lg-2" style="margin: 10px">
                    <CardHeader style="background-color: limegreen">Slot 1</CardHeader>
                    <CardBody style="background-color: #99ff99">
                        <CardText>
                            <p>Price: </p>
                            <p>Worker: </p>
                            <p>Worker Rating: </p>
                        </CardText>
                        <button>Book Slot</button>
                    </CardBody>
                </Card>
                <Card class="col-lg-2" style="margin: 10px">
                    <CardHeader style="background-color: limegreen">Slot 1</CardHeader>
                    <CardBody style="background-color: #99ff99">
                        <CardText>
                            <p>Price: </p>
                            <p>Worker: </p>
                            <p>Worker Rating: </p>
                        </CardText>
                        <button>Book Slot</button>
                    </CardBody>
                </Card>
                <Card class="col-lg-2" style="margin: 10px">
                    <CardHeader style="background-color: limegreen">Slot 1</CardHeader>
                    <CardBody style="background-color: #99ff99">
                        <CardText>
                            <p>Price: </p>
                            <p>Worker: </p>
                            <p>Worker Rating: </p>
                        </CardText>
                        <button>Book Slot</button>
                    </CardBody>
                </Card>
                <Card class="col-lg-2" style="margin: 10px">
                    <CardHeader style="background-color: limegreen">Slot 1</CardHeader>
                    <CardBody style="background-color: #99ff99">
                        <CardText>
                            <p>Price: </p>
                            <p>Worker: </p>
                            <p>Worker Rating: </p>
                        </CardText>
                        <button>Book Slot</button>
                    </CardBody>
                </Card>
                <Card class="col-lg-2" style="margin: 10px">
                    <CardHeader style="background-color: limegreen">Slot 1</CardHeader>
                    <CardBody style="background-color: #99ff99">
                        <CardText>
                            <p>Price: </p>
                            <p>Worker: </p>
                            <p>Worker Rating: </p>
                        </CardText>
                        <button>Book Slot</button>
                    </CardBody>
                </Card>
            </div>
        {/if}
    {/if}
</main>

<style>
    h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 2em;
		font-weight: 100;
	}

    button {
        border-radius: 5px;
        margin: 5px;
    }

    .orange {
        background-color: rgb(255, 112, 60);
    }

    .green {
        background-color: chartreuse;
    }

    .blue {
        background-color: royalblue;
        color: white;
        border: 2px solid black;
    }

    .flex {
        margin-left: 75px;
        display: flex;
    }
</style>