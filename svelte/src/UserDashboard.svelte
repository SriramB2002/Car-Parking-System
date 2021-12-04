<svelte:head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</svelte:head>

<script>
    import { navigate } from "svelte-navigator";
    import { Navbar, NavbarBrand, Nav, NavItem, Card, CardHeader, CardBody, CardText, Modal, ModalHeader, ModalBody } from "sveltestrap/src";
    import { login } from "./stores";

    const signOut = () => {
        const auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(() => {
            console.log("SIGNED OUT");
            login.set({
                id: -1,
                first_name: "",
                last_name: "",
                username: "",
                password: "",
                address: "",
                car_reg: "",
                mobile: "",
                email: ""
            });
            navigate("UserLogin");
        });
    }
    
    const nav = () => {
        navigate("UserProfile");
    }

    let bool = false;
    const check = () => {
        bool = true;
    }

    let bool1 = false;
    const openmodal = () => {
        bool1 = !bool1;
    }
    let choice;

    async function getSpaces() {
        const res = await fetch("http://localhost:8080/getSpaces");
        const resp = await res.json();

        return resp;
    }

    let promise = getSpaces();
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
    <br>
    <h3>Hello {$login.first_name} {$login.last_name}!</h3>
    <h1>Book your slot here</h1>
    <form on:submit|preventDefault={check}>
        <h6>Location</h6>
        {#await promise}
            <p>Loading spaces...</p>
        {:then spaces} 
        <select bind:value={choice} required>
            <option></option>
            {#each spaces as elem}
                <option>{elem}</option>
            {/each}
        </select>
            
        {/await}
        
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
                            <p>Car Type: </p>
                        </CardText>
                        <button on:click={openmodal}>Select Slot</button>
                    </CardBody>
                </Card>
                <Card class="col-lg-2" style="margin: 10px">
                    <CardHeader style="background-color: limegreen">Slot 1</CardHeader>
                    <CardBody style="background-color: #99ff99">
                        <CardText>
                            <p>Price: </p>
                            <p>Car Type: </p>
                        </CardText>
                        <button on:click={openmodal}>Select Slot</button>
                    </CardBody>
                </Card>
                <Card class="col-lg-2" style="margin: 10px">
                    <CardHeader style="background-color: limegreen">Slot 1</CardHeader>
                    <CardBody style="background-color: #99ff99">
                        <CardText>
                            <p>Price: </p>
                            <p>Car Type: </p>
                        </CardText>
                        <button on:click={openmodal}>Select Slot</button>
                    </CardBody>
                </Card>
                <Card class="col-lg-2" style="margin: 10px">
                    <CardHeader style="background-color: limegreen">Slot 1</CardHeader>
                    <CardBody style="background-color: #99ff99">
                        <CardText>
                            <p>Price: </p>
                            <p>Car Type: </p>
                        </CardText>
                        <button on:click={openmodal}>Select Slot</button>
                    </CardBody>
                </Card>
                <Card class="col-lg-2" style="margin: 10px">
                    <CardHeader style="background-color: limegreen">Slot 1</CardHeader>
                    <CardBody style="background-color: #99ff99">
                        <CardText>
                            <p>Price: </p>
                            <p>Car Type: </p>
                        </CardText>
                        <button on:click={openmodal}>Select Slot</button>
                    </CardBody>
                </Card>
            </div>
        {/if}
    {/if}
    {#if bool1}
        <Modal isOpen={bool1}>
            <ModalHeader>Preferences</ModalHeader>
            <ModalBody>
                <p>Services: 
                    <select>
                        <option></option>
                        <option>Dry Cleaning</option>
                        <option>Car Washing</option>
                        <option>Repair & Maintenance</option>
                    </select>
                </p>
            </ModalBody>
        </Modal>
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