async function calculateStats() {
    const input = document.getElementById('dataInput').value;
    
    // Parse input
    const strArray = input.split(/[,;\s]+/).filter(Boolean);
    const data = strArray.map(Number).filter(n => !isNaN(n));

    if (data.length === 0) {
        alert("Mohon masukkan angka yang valid!");
        return;
    }

    try {
        const btn = document.getElementById('calcBtn');
        btn.textContent = 'Menghitung...';
        btn.disabled = true;

        const response = await fetch('/api/calculate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ data: data })
        });

        if (response.ok) {
            const result = await response.json();
            
            document.getElementById('res-mean').textContent = result.mean.toFixed(2);
            document.getElementById('res-median').textContent = result.median.toFixed(2);
            document.getElementById('res-mode').textContent = result.mode.length > 0 ? result.mode.join(', ') : '-';
            document.getElementById('res-std').textContent = result.stdDeviation.toFixed(2);
            document.getElementById('res-min').textContent = result.min;
            document.getElementById('res-max').textContent = result.max;

            document.getElementById('result-section').classList.remove('hidden');
        } else {
            alert('Gagal menghitung statistik. Terjadi kesalahan pada server.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Terjadi kesalahan jaringan.');
    } finally {
        const btn = document.getElementById('calcBtn');
        btn.textContent = 'Hitung Statistik';
        btn.disabled = false;
    }
}
