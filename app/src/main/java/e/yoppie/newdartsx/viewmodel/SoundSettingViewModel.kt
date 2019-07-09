package e.yoppie.newdartsx.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.SoundModel

class SoundSettingViewModel : ViewModel() {
    val isAllSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isBgmSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isInBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isOthersSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    var bullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()
    var inBullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()
    var bullSoundHandler = {}
    var inBullSoundHandler = {}

    init {
        isAllSwitchChecked.value = false
        isBgmSwitchChecked.value = false
        isBullSwitchChecked.value = false
        isInBullSwitchChecked.value = false
        isOthersSwitchChecked.value = false
        SoundModel.getAll().forEach {
            val mutableLiveData1 = MutableLiveData<Int>()
            val mutableLiveData2 = MutableLiveData<Int>()
            mutableLiveData1.value = it.backGroundId
            mutableLiveData2.value = it.backGroundId
            bullButtonBackGrounds[it.id] = mutableLiveData1
            inBullButtonBackGrounds[it.id] = mutableLiveData2
        }
    }

    fun onClickBullButton(id: Int) {
        bullSoundHandler()
        isBullSwitchChecked.postValue(true)
        bullButtonBackGrounds.forEach {
            if (id == it.key) it.value.postValue(R.drawable.square_button2_selector)
            else it.value.postValue(R.drawable.square_button_selector)
        }
        if (isBgmSwitchChecked.value!!
            && isInBullSwitchChecked.value!!
            && isOthersSwitchChecked.value!!
        ) isAllSwitchChecked.postValue(true)
    }

    fun onClickInBullButton(id: Int) {
        inBullSoundHandler()
        isInBullSwitchChecked.postValue(true)
        inBullButtonBackGrounds.forEach {
            if (id == it.key) it.value.postValue(R.drawable.square_button2_selector)
            else it.value.postValue(R.drawable.square_button_selector)
        }
        if (isBgmSwitchChecked.value!!
            && isBullSwitchChecked.value!!
            && isOthersSwitchChecked.value!!
        ) isAllSwitchChecked.postValue(true)
    }

    fun onClickSwitch(view: View) {
        when (view.id) {
            R.id.all_switch -> {
                if (isAllSwitchChecked.value!!) {
                    bullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                    inBullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                } else {
                    bullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                    inBullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                }
                isAllSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isBgmSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isBullSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isInBullSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isOthersSwitchChecked.postValue(!isAllSwitchChecked.value!!)
            }
            R.id.bgm_switch -> {
                if (!isBgmSwitchChecked.value!!) {
                    if (isBullSwitchChecked.value!!
                        && isInBullSwitchChecked.value!!
                        && isOthersSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    isAllSwitchChecked.postValue(false)
                }
                isBgmSwitchChecked.postValue(!isBgmSwitchChecked.value!!)
            }
            R.id.bull_switch -> {
                if (!isBullSwitchChecked.value!!) {
                    bullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                    if (isBgmSwitchChecked.value!!
                        && isInBullSwitchChecked.value!!
                        && isOthersSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    bullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                    isAllSwitchChecked.postValue(false)
                }
                isBullSwitchChecked.postValue(!isBullSwitchChecked.value!!)
            }
            R.id.in_bull_switch -> {
                if (!isInBullSwitchChecked.value!!) {
                    inBullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                    if (isBgmSwitchChecked.value!!
                        && isBullSwitchChecked.value!!
                        && isOthersSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    inBullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                    isAllSwitchChecked.postValue(false)
                }
                isInBullSwitchChecked.postValue(!isInBullSwitchChecked.value!!)
            }
            R.id.others_switch -> {
                if (!isOthersSwitchChecked.value!!) {
                    if (isBgmSwitchChecked.value!!
                        && isBullSwitchChecked.value!!
                        && isInBullSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    isAllSwitchChecked.postValue(false)
                }
                isOthersSwitchChecked.postValue(!isOthersSwitchChecked.value!!)
            }
        }
    }
}
