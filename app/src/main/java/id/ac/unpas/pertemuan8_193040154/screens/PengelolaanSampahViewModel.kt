package id.ac.unpas.pertemuan8_193040154.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.pertemuan8_193040154.model.SetoranSampah
import id.ac.unpas.pertemuan8_193040154.persistences.SetoranSampahDao
import javax.inject.Inject

@HiltViewModel
class PengelolaanSampahViewModel @Inject constructor(private val
setoranSampahDao: SetoranSampahDao) : ViewModel()
{
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = isLoading

    private val _success: MutableLiveData<Boolean> = MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success

    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = toast

    private val _list: MutableLiveData<List<SetoranSampah>> = MutableLiveData()
    val list : LiveData<List<SetoranSampah>> get() = _list

    suspend fun loadItems()
    {
        isLoading.postValue(true)
        setoranSampahRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            isLoading.postValue(false)
            _list.postValue(list)
        })
    }

    suspend fun insert(tanggal: String,
                       nama: String,
                       berat: String) {
        _isLoading.postValue(true)
        setoranSampahRepository.insert(tanggal, nama, berat, onError = { item, message ->
            toast.postValue(message)
            _isLoading.postValue(false)
        }, onSuccess = {
            _isLoading.postValue(false)
            _success.postValue(true)
        })
    }
}
