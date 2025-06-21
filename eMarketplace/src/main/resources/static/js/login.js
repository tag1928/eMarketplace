submit_button.addEventListener("click", handleSubmit);

const url = "/user/login";
const headers = {"Content-Type": "application/json"};

async function handleSubmit()
{
    if (username.value.trim() === "")
    {
        alert("Username is required");
        return;
    }

    if (password.value.trim() === "")
    {
        alert("Password is required");
    }

    const json =
    {
        username: username.value.trim(),
        password: password.value.trim(),
    };

    const requestBody = {method: "POST", headers: headers, body: JSON.stringify(json)};

    const response = await fetch(url, requestBody);

    if (response.status === 401)
    {
        alert("Incorrect username or password");
        return;
    }

    localStorage.setItem("username", username.value.trim());
    alert(`Welcome ${username.value.trim()}!`);
}