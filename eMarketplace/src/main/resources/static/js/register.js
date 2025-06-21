sumbit_button.addEventListener("click", handleSubmit);

const url = "/user/register";
const headers = {"Content-Type": "application/json"};

async function handleSubmit()
{
    if (username.value.trim() === "")
    {
        alert("Username is required");
        return;
    }

    if (email.value.trim() === "")
    {
        alert("Email is required");
        return;
    }

    if (password.value.trim() === "")
    {
        alert("Password is required");
        return;
    }

    if (birthday.value.trim() === "")
    {
        alert("Birthday is required");
        return;
    }

    const json =
    {
        username: username.value.trim(),
        email: email.value.trim(),
        password: password.value.trim(),
        birthday: birthday.value.trim()
    };

    const requestContent = {method: "POST", headers: headers, body: JSON.stringify(json)};

    const response = await fetch(url, requestContent);

    if (response.status === 400)
    {
        alert("Bad credentials");
        return;
    }

    localStorage.setItem("username", username.value.trim());
    alert(`Welcome ${username.value.trim()}!`);
}