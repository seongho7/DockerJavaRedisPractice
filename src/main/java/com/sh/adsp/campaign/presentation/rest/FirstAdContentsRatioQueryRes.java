package com.sh.adsp.campaign.presentation.rest;

import com.sh.adsp.campaign.domain.AdContentsRatio;

record FirstAdContentsRatioQueryRes(String firstAdRatio) {
  FirstAdContentsRatioQueryRes(AdContentsRatio ratio) {
    this(ratio.getAd() + ":" + ratio.getContents());
  }
}
